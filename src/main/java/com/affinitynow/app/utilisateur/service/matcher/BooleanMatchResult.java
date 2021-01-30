package com.affinitynow.app.utilisateur.service.matcher;

public class BooleanMatchResult implements MatchResult {
    private final boolean success;

    @Override
    public boolean isSuccess() {
      return success;
    }

    public BooleanMatchResult(boolean success) {
      this.success = success;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (success ? 1231 : 1237);
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
      BooleanMatchResult other = (BooleanMatchResult) obj;
      if (success != other.success)
        return false;
      return true;
    }

    @Override
    public int compareTo(MatchResult o) {
      return (this.success == o.isSuccess()) ? 0 : (o.isSuccess() ? 1 : -1);
    }
}
