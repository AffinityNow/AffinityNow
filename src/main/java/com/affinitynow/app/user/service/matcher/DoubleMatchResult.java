package com.affinitynow.app.user.service.matcher;

import java.util.Set;
import com.affinitynow.app.model.Knowledge;
import com.affinitynow.app.model.User;

public class DoubleMatchResult<Double> extends AbstractMatchResult<Double> {
    private Double _result;

    protected DoubleMatchResult(Set<Knowledge> commonTopics, User user, User mUser, Double result, double quality) {
        super(mUser, user, commonTopics, quality);
        this._result = result;
    }

    @Override
    public Double result() {
        return _result;
    }

    @Override
    public int compareTo(IMatchResult<Double> o) {
       return Integer.compare(Integer.parseInt(String.valueOf(_result)), Integer.parseInt(String.valueOf(o.result()))) ;
    }

    public Double get_result() {
        return _result;
    }

    public void set_result(Double _result) {
        this._result = _result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_result == null) ? 0 : _result.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DoubleMatchResult<Double> other = (DoubleMatchResult<Double>) obj;
        if (_result == null) {
            if (other._result != null)
                return false;
        } else if (!_result.equals(other._result))
            return false;
        return true;
    }
}
