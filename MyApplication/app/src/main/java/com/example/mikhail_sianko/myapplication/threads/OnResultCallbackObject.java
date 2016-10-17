package com.example.mikhail_sianko.myapplication.threads;

import com.example.mikhail_sianko.myapplication.threads.operation.WorkerOperation;

public interface OnResultCallbackObject {

    void onSuccess(WorkerOperation.Result result);

    void onError(Exception e);
}
