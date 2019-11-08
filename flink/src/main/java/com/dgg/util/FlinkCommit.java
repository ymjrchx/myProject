package com.dgg.util;

import org.apache.flink.api.common.typeutils.TypeSerializer;
import org.apache.flink.streaming.api.functions.sink.TwoPhaseCommitSinkFunction;

public class FlinkCommit extends TwoPhaseCommitSinkFunction {

    public FlinkCommit(TypeSerializer transactionSerializer, TypeSerializer contextSerializer) {
        super(transactionSerializer, contextSerializer);
    }

    @Override
    protected void invoke(Object o, Object o2, Context context) throws Exception {

    }

    @Override
    protected Object beginTransaction() throws Exception {
        return null;
    }

    @Override
    protected void preCommit(Object o) throws Exception {

    }

    @Override
    protected void commit(Object o) {

    }

    @Override
    protected void abort(Object o) {

    }
}
