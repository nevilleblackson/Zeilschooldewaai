package blackson.n.zeilschooldewaai.fragments.views;

import java.util.List;

import blackson.n.zeilschooldewaai.models.Cursist;

public interface CursesDetailsView {

    void onDataProvided(final List<Cursist> pData);

    void onFailure(String pDetails);
}

