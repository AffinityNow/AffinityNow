package com.affinitynow.app.user.service.matcher;

import java.util.Set;

import com.affinitynow.app.model.Knowledge;
import com.affinitynow.app.model.User;

public class DoubleMatchResult<Double> extends AbstractMatchResult<Double> {
    private Double _result;

    protected DoubleMatchResult(Set<Knowledge> commonTopics, User user, User mUser, Double result) {
        super(commonTopics, user, mUser);
        this._result = result;
    }

    @Override
    public Double result() {
        return _result;
    }

    @Override
    public int compareTo(IMatchResult<Double> o) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
