/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.training2016.sample.backend;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.repackaged.com.google.common.base.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StoreServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("application/javascript");
        String name = req.getParameter("entity");
        String action = req.getParameter("action");
        if (action != null && action.equals("del")) {
            try {
                doDelete(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            }
            return;
        }
        Query q = new Query(name);
        initFilters(req, req.getParameterNames(), q);
        Iterable<Entity> entities = listRawEntity(q, false);
        JSONArray jsonArray = new JSONArray();
        for (Entity entity : entities) {
            Map<String, Object> properties = entity.getProperties();
            JSONObject jsonObject = new JSONObject();
            try {
                Set<String> strings = properties.keySet();
                for (String key : strings) {
                    jsonObject.put(key, properties.get(key));
                }
                long id = entity.getKey().getId();
                jsonObject.put("_id", id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
        }
        resp.getWriter().println(jsonArray.toString());
    }

    public Iterable<Entity> listRawEntity(Query q, Boolean returnKeysOnly) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        if (returnKeysOnly) {
            q.setKeysOnly();
        }
        PreparedQuery pq = datastore.prepare(q);
        return pq.asIterable();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("entity");
        resp.setContentType("application/javascript");
        if (name == null) {
            JSONModel jsonObject = new JSONModel();
            jsonObject.set("error", "Please enter a entity");
            resp.getWriter().println(jsonObject.toString());
            return;
        }
        Enumeration parameterNames = req.getParameterNames();
        String _id = req.getParameter("_id");
        if (_id != null && _id.length() > 0) {
            Key key = KeyFactory.createKey(name, Long.valueOf(_id));
            DatastoreServiceFactory.getDatastoreService().delete(key);
        } else {
            final Query query = new Query(name);
            query.setKeysOnly();
            initFilters(req, parameterNames, query);
            deleteAllObjects(query);
        }
        JSONModel jsonObject = new JSONModel();
        jsonObject.set("success", name + " removed");
        resp.getWriter().println(jsonObject.toString());
    }

    private void initFilters(HttpServletRequest req, Enumeration parameterNames, Query query) {
        while (parameterNames.hasMoreElements()) {
            String param = (String) parameterNames.nextElement();
            if (param.equals("entity") || param.equals("action")) {
                continue;
            }
            String parameter = req.getParameter(param);
            query.addFilter(param, Query.FilterOperator.EQUAL, parameter);
        }
    }

    public void deleteAllObjects(Query query){
        final DatastoreService dss=DatastoreServiceFactory.getDatastoreService();
        final ArrayList<Key> keys=new ArrayList<>();
        for (  final Entity entity : dss.prepare(query).asIterable(FetchOptions.Builder.withLimit(100000))) {
            keys.add(entity.getKey());
        }
        dss.delete(keys);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("del")) {
            try {
                doDelete(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            }
            return;
        }
        String name = req.getParameter("entity");
        String _id = req.getParameter("_id");
        resp.setContentType("application/javascript");
        if (name == null) {
            JSONModel jsonObject = new JSONModel();
            jsonObject.set("error", "Please enter a entity");
            resp.getWriter().println(jsonObject.toString());
            return;
        }

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Entity entity = null;
        if (_id == null || _id.length() == 0) {
            entity = new Entity(name);
        } else {
            Key key = KeyFactory.createKey(name, Long.valueOf(_id));
            try {
                entity = datastore.get(key);
            } catch (EntityNotFoundException e) {

            }
            if (entity == null) {
                JSONModel jsonObject = new JSONModel();
                jsonObject.set("error", "Entity is not found");
                resp.getWriter().println(jsonObject.toString());
                return;
            }
        }

        Enumeration parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String param = (String) parameterNames.nextElement();
            if (param.equals("entity")) {
                continue;
            }
            String parameter = req.getParameter(param);
            entity.setProperty(param, parameter);
        }
        datastore.put(entity);
        JSONModel jsonObject = new JSONModel();
        jsonObject.set("success", name + " added");
        resp.getWriter().println(jsonObject.toString());
    }
}
