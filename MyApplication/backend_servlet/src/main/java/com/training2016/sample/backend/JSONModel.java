/**
 *
 */
package com.training2016.sample.backend;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Abstract base class for all model classes.
 * Contains common accessors and mutators for JSON values and basic implementation of Parcelable interface.
 *
 * @author Uladzimir_Klyshevich
 */
public class JSONModel {


    /**
     * The constant logging tag.
     */
    public static final String TAG = JSONModel.class.getName();

    /**
     * The underlying JSON object.
     */
    private JSONObject jo;

    /**
     * Instantiates a new base model.
     */
    public JSONModel() {
        jo = new JSONObject();
    }

    /**
     * Instantiates a new base model with underlying JSON object as String.
     *
     * @param json the json
     * @throws JSONException the jSON exception
     */
    public JSONModel(final String json) throws JSONException {
        jo = new JSONObject(json);
    }

    /**
     * Instantiates a new base model with underlying JSON object.
     *
     * @param json the json object
     */
    public JSONModel(final JSONObject json) {
        if (json == null) {
            throw new IllegalArgumentException("JSONObject argument is null");
        }
        jo = json;
    }

    /**
     * Sets the value for key.
     *
     * @param key   the key
     * @param value the value
     */
    public final void set(final String key, final Object value) {
        try {
            synchronized (jo) {
                if (value == null) {
                    jo.remove(key);
                } else {
                    jo.put(key, value);
                }
            }
        } catch (JSONException e) {
        }
    }

    protected final void setModel(final String key, final JSONModel model) {
        synchronized (jo) {
            if (model == null) {
                jo.remove(key);
            } else {
                try {
                    jo.put(key, model.getJSONObject());
                } catch (JSONException e) {
                }
            }
        }
    }

    /**
     * Gets the value for key.
     *
     * @param key the key
     * @return the object
     */
    protected final Object get(final String key) {
        try {
            if (!jo.isNull(key)) {
                return jo.get(key);
            }
        } catch (JSONException e) {
        }
        return null;
    }

    /**
     * Gets the String value.
     *
     * @param key the key
     * @return the string
     */
    protected final String getString(final String key) {
        try {
            if (!jo.isNull(key)) {
                return jo.getString(key);
            }
        } catch (JSONException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return null;
    }

    /**
     * Gets the inner string from nested JSON object.
     *
     * @param jkey the nested JSON object jkey
     * @param key  the String key
     * @return the inner string
     */
    protected final String getInnerString(final String jkey, final String key) {
        try {
            JSONObject ijo = getJSONObject(jkey);
            if (ijo == null) {
                return null;
            }
            return ijo.getString(key);
        } catch (JSONException e) {
        }
        return null;

    }

    /**
     * Gets the Integer value.
     *
     * @param key the key
     * @return the int
     */
    protected final Integer getInt(final String key) {
        try {
            if (!jo.isNull(key)) {
                return jo.getInt(key);
            }
        } catch (JSONException e) {
        }
        return null;
    }

    /**
     * Gets the Double value.
     *
     * @param key the key
     * @return the double
     */
    protected final Double getDouble(final String key) {
        try {
            if (!jo.isNull(key)) {
                return jo.getDouble(key);
            }
        } catch (JSONException e) {
        }
        return null;
    }

    /**
     * Gets the Boolean value.
     *
     * @param key the key
     * @return the boolean, default Boolean.FALSE
     */
    protected final Boolean getBoolean(final String key) {
        try {
            if (!jo.isNull(key)) {
                return jo.getBoolean(key);
            }
        } catch (JSONException e) {
        }
        return Boolean.FALSE;
    }

    /**
     * Gets the Long value.
     *
     * @param key the key
     * @return the long
     */
    protected final Long getLong(final String key) {
        try {
            if (!jo.isNull(key)) {
                return jo.getLong(key);
            }
        } catch (JSONException e) {

        }
        return null;
    }


    /**
     * Gets the JSON object value.
     *
     * @param key the key
     * @return the jSON object
     */
    protected final JSONObject getJSONObject(final String key) {
        try {
            if (!jo.isNull(key)) {
                return jo.getJSONObject(key);
            }
        } catch (JSONException e) {
        }
        return null;
    }

    /**
     * Gets the JSONArray value.
     *
     * @param key the key
     * @return the JSONArray
     */
    protected final JSONArray getJSONArray(final String key) {
        try {
            if (!jo.isNull(key)) {
                return jo.getJSONArray(key);
            }
        } catch (JSONException e) {
        }
        return null;
    }

    /**
     * Gets the JSONArray value size.
     *
     * @param key the key
     * @return the jSON array size
     */
    protected final Integer getJSONArraySize(final String key) {
        try {
            if (!jo.isNull(key)) {
                return jo.getJSONArray(key).length();
            }
        } catch (JSONException e) {
        }
        return null;
    }

    /**
     * Gets the underlying JSON object.
     *
     * @return the jSON object
     */
    public final JSONObject getJSONObject() {
        return jo;
    }


    public void setJO(JSONObject object) {
        this.jo = object;
    }

    @Override
    public String toString() {
        if (jo != null) {
            return jo.toString();
        }
        return super.toString();
    }
}