package com.example.mikhail_sianko.myapplication.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikhail_sianko.myapplication.BuildConfig;
import com.example.mikhail_sianko.myapplication.R;
import com.example.mikhail_sianko.myapplication.constants.TwitterConstants;
import com.example.mikhail_sianko.myapplication.gson.DateConverter;
import com.example.mikhail_sianko.myapplication.model.HttpRequestModel;
import com.example.mikhail_sianko.myapplication.model.TwitterSearchResponse;
import com.example.mikhail_sianko.myapplication.model.gson.TwitterSearchGSONResponse;
import com.example.mikhail_sianko.myapplication.threads.OnResultCallback;
import com.example.mikhail_sianko.myapplication.threads.ProgressCallback;
import com.example.mikhail_sianko.myapplication.threads.ThreadManager;
import com.example.mikhail_sianko.myapplication.threads.operation.DBOperation;
import com.example.mikhail_sianko.myapplication.threads.operation.HttpGetRequest;
import com.example.mikhail_sianko.myapplication.threads.operation.HttpPostOperation;
import com.example.mikhail_sianko.myapplication.threads.operation.WorkerOperation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class MainActivity extends AppCompatActivity implements Contract.View {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Contract.Presenter presenter;

    private TextView responseView;
    private ProgressBar progressBar;

    private String mAccessToken;

    //TODO should be singleton
    private ThreadManager threadManager = new ThreadManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter(this);
        setContentView(R.layout.activity_main);

        responseView = (TextView) findViewById(R.id.responseView);
        progressBar = ((ProgressBar) findViewById(R.id.progressIndicator));

        View btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpRequestModel httpPostOperation = new HttpRequestModel();

                httpPostOperation.setUrl("https://api.twitter.com/oauth2/token");

                Map<String, String> headers = new ConcurrentHashMap<String, String>();
                headers.put("User-Agent", "My Twitter App v1.0.23");
                String authorizationSecret = TwitterConstants.CONSUMER_KEY + ":" + TwitterConstants.CONSUMER_SECRET;
                headers.put("Authorization", "Basic " + Base64.encodeToString(authorizationSecret.getBytes(), Base64.DEFAULT));
                headers.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

                httpPostOperation.setBody("grant_type=client_credentials");

                httpPostOperation.setHeaders(headers);
                threadManager.execute(new HttpPostOperation(), httpPostOperation, new OnResultCallback<String, Void>() {
                    @Override
                    public void onSuccess(String pResponse) {
                        Log.d(TAG, "onSuccess = " + pResponse);
                        try {
                            JSONObject jsonObject = new JSONObject(pResponse);
                            mAccessToken = jsonObject.getString("access_token");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e(TAG, "onError message=" + e.getMessage(), e);
                    }

                    @Override
                    public void onProgressChanged(Void aVoid) {

                    }
                });
            }
        });

        btnLogin.callOnClick();

        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HttpRequestModel httpGetOperation = new HttpRequestModel();
                httpGetOperation.setUrl("https://api.twitter.com/1.1/search/tweets.json?q=%23freebandnames");

                Map<String, String> headers = new ConcurrentHashMap<String, String>();
                headers.put("Authorization", "Bearer "+ mAccessToken);

                httpGetOperation.setHeaders(headers);

                threadManager.execute(new HttpGetRequest(), httpGetOperation, new OnResultCallback<String, Void>() {


                    @Override
                    public void onSuccess(String s) {
                        Log.d(TAG, "search success=" + s);

                        parseJsonOverJSONObject(s);

                        parseJsonOverGson(s);

                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d(TAG, "search error", e);
                    }

                    @Override
                    public void onProgressChanged(Void aVoid) {

                    }
                });
            }
        });



    }

    private void parseJsonOverJSONObject(String response) {
        try {
            TwitterSearchResponse statuses = new TwitterSearchResponse(response);
            statuses.getTwitterSearchStatuses();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJsonOverGson(String response) {
        Gson gson =  new GsonBuilder().registerTypeAdapter(Date.class, new DateConverter()).create();

        TwitterSearchGSONResponse twitterSearchResponse = gson.fromJson(response, TwitterSearchGSONResponse.class);
        twitterSearchResponse.getStatuses();

    }

    private void doThreadStuff() {
        threadManager.execute(new DBOperation(), 2, new OnResultCallback<String, Integer>() {

            @Override
            public void onProgressChanged(final Integer integer) {
                Toast.makeText(MainActivity.this, "progress " + integer, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(final String result) {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(final Exception e) {
                Toast.makeText(MainActivity.this, "error " + e, Toast.LENGTH_SHORT).show();
            }
        });
        threadManager.execute(new WorkerOperation(), "do something", new OnResultCallback<WorkerOperation.Result, Integer>() {

            @Override
            public void onProgressChanged(final Integer integer) {
                Toast.makeText(MainActivity.this, "progress " + integer, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(final WorkerOperation.Result result) {
                Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(final Exception e) {
                Toast.makeText(MainActivity.this, "error " + e, Toast.LENGTH_SHORT).show();
            }
        });

        new AsyncTask<String, Integer, List<WorkerOperation.Result>>() {

            private Exception e;

            @Override
            protected List<WorkerOperation.Result> doInBackground(final String... params) {
                try {
                    List<WorkerOperation.Result> results = new ArrayList<WorkerOperation.Result>();
                    for (final String param : params) {
                        results.add(new WorkerOperation().doing(param, new ProgressCallback<Integer>() {

                            @Override
                            public void onProgressChanged(final Integer integer) {
                                publishProgress(integer);
                            }
                        }));
                    }
                    return results;
                } catch (Exception e) {
                    this.e = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(final List<WorkerOperation.Result> results) {
                if (e == null) {
                    for (final WorkerOperation.Result result : results) {
                        Log.d(TAG, result.toString());
                    }
                }
                super.onPostExecute(results);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "do something in AsyncTask", "twice");

        LoaderManager supportLoaderManager = getSupportLoaderManager();
        Bundle bundle = new Bundle();
        bundle.putString("param", "My best string");
        supportLoaderManager.restartLoader(0, bundle, new LoaderManager.LoaderCallbacks<WorkerOperation.Result>() {

            @Override
            public Loader<WorkerOperation.Result> onCreateLoader(final int id, final Bundle args) {
                return new AsyncTaskLoader<WorkerOperation.Result>(MainActivity.this) {

                    private WorkerOperation.Result mResult;

                    @Override
                    public WorkerOperation.Result loadInBackground() {
                        try {
                            return new WorkerOperation().doing(args.getString("param"), new ProgressCallback<Integer>() {

                                @Override
                                public void onProgressChanged(final Integer integer) {
                                    Log.d(TAG, "onprogress changed " + integer);
                                }
                            });
                        } catch (final Exception e) {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "error " + e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            return null;
                        }
                    }

                    @Override
                    public void deliverResult(WorkerOperation.Result data) {
                        if (isReset()) {
                            // The Loader has been reset; ignore the result and invalidate the data.
                            return;
                        }

                        // Hold a reference to the old data so it doesn't get garbage collected.
                        // We must protect it until the new data has been delivered.
                        WorkerOperation.Result oldData = mResult;
                        mResult = data;

                        if (isStarted()) {
                            // If the Loader is in a started state, deliver the results to the
                            // client. The superclass method does this for us.
                            super.deliverResult(data);
                        }

                    }

                    @Override
                    protected void onStartLoading() {
                        if (mResult != null) {
                            // Deliver any previously loaded data immediately.
                            deliverResult(mResult);
                        }


                        if (takeContentChanged() || mResult == null) {
                            // When the observer detects a change, it should call onContentChanged()
                            // on the Loader, which will cause the next call to takeContentChanged()
                            // to return true. If this is ever the case (or if the current data is
                            // null), we force a new load.
                            forceLoad();
                        }
                    }

                    @Override
                    protected void onStopLoading() {
                        // The Loader is in a stopped state, so we should attempt to cancel the
                        // current load (if there is one).
                        cancelLoad();

                        // Note that we leave the observer as is. Loaders in a stopped state
                        // should still monitor the data source for changes so that the Loader
                        // will know to force a new load if it is ever started again.
                    }

                    @Override
                    protected void onReset() {
                        // Ensure the loader has been stopped.
                        onStopLoading();

                        // At this point we can release the resources associated with 'mData'.
                        if (mResult != null) {
                            mResult = null;
                        }

                    }

                    @Override
                    public void onCanceled(WorkerOperation.Result data) {
                        // Attempt to cancel the current asynchronous load.
                        super.onCanceled(data);

                    }
                };
            }

            @Override
            public void onLoadFinished(final Loader<WorkerOperation.Result> loader, final WorkerOperation.Result data) {
                Log.d(TAG, "result data from Loader " + data);
            }

            @Override
            public void onLoaderReset(final Loader<WorkerOperation.Result> loader) {

            }
        });
        presenter.onReady();
    }

    private static class MyLog {

        public static void d(String tag, Callable<String> message) {
            if (BuildConfig.DEBUG) {
                try {
                    Log.d(tag, message.call());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void showData(String data) {
        responseView.setText(data);
    }

    @Override
    public void showError(String message) {
        new AlertDialog.Builder(this).setMessage(message).create().show();
    }

    @Override
    public void showProgress(boolean isInProgress) {
        progressBar.setVisibility(isInProgress ? View.VISIBLE : View.GONE);
    }
}
