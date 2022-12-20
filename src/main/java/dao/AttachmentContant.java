package dao;

import model.Problem;

import java.util.List;

public class AttachmentContant extends BaseDatabaseConnection implements BaseDatabase<Problem>{
    @Override
    public boolean addObject(Problem problem) {
        return false;
    }

    @Override
    public List<Problem> getObjectList() {
        return null;
    }
}
