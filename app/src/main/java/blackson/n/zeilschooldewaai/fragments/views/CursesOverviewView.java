package blackson.n.zeilschooldewaai.fragments.views;

import blackson.n.zeilschooldewaai.models.SoortenCursus;

public interface CursesOverviewView {

    void onFailure(String pDetails);

    void addItem(SoortenCursus pCursus);
}
