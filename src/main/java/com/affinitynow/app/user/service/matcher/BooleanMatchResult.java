package com.affinitynow.app.user.service.matcher;

import java.util.Set;

import com.affinitynow.app.model.Knowledge;
import com.affinitynow.app.model.User;

public class BooleanMatchResult<Boolean> extends AbstractMatchResult<Boolean> {
    private Boolean _result;

    public BooleanMatchResult(Set<Knowledge> commonTopics, User user, User mUser, Boolean result, double quality) {
        super(mUser, user, commonTopics, quality);
        this._result = result;
    }

    @Override
    public Boolean result() {
        return _result;
    }

    @Override
    public int compareTo(IMatchResult<Boolean> o) {
        return (this._result == o.result()) ? 0 : ((boolean) o.result() ? 1 : -1);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this._result == null) ? 0 : this._result.hashCode());
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
        BooleanMatchResult<Boolean> other = (BooleanMatchResult<Boolean>) obj;
        if (_result == null) {
            if (other._result != null)
                return false;
        } else if (!_result.equals(other._result))
            return false;
        return true;
    }

    public Boolean get_result() {
        return _result;
    }

    public void set_result(Boolean _result) {
        this._result = _result;
    }
}
