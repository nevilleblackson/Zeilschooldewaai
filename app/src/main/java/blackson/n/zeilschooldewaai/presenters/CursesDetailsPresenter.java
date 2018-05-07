package blackson.n.zeilschooldewaai.presenters;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import blackson.n.zeilschooldewaai.fragments.views.CursesDetailsView;
import blackson.n.zeilschooldewaai.models.CursistCursus;
import blackson.n.zeilschooldewaai.models.Cursus;
import blackson.n.zeilschooldewaai.models.SoortenCursus;
import blackson.n.zeilschooldewaai.models.Cursist;

public class CursesDetailsPresenter extends BasePresenter<CursesDetailsView> {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    public CursesDetailsPresenter(final CursesDetailsView pView) {
        super(pView);
    }

    public void provide(final SoortenCursus pCursus) {
        database.getReference("cursus").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot pDataSnapshot) {
                final List<Long> cursuscodes = new ArrayList<>();

                if (pDataSnapshot.getChildren() != null) {
                    for (DataSnapshot snapshot : pDataSnapshot.getChildren()) {
                        Cursus cursus = snapshot.getValue(Cursus.class);
                        if (cursus == null || !cursus.getSoortcode().equals(pCursus.getSoortcode()))
                            continue;
                        cursuscodes.add(cursus.getCursuscode());
                    }
                }

                database.getReference("cursistCursus").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot pDataSnapshot) {
                        final List<String> emails = new ArrayList<>();
                        if (pDataSnapshot.getChildren() != null) {
                            for (DataSnapshot snapshot : pDataSnapshot.getChildren()) {
                                CursistCursus value = snapshot.getValue(CursistCursus.class);
                                if (value == null || !cursuscodes.contains(value.getCursusCode()))
                                    continue;
                                emails.add(value.getEmail());
                            }
                        }
                        getUsers(emails);
                    }

                    @Override
                    public void onCancelled(final DatabaseError pDatabaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(final DatabaseError pDatabaseError) {

            }
        });
    }

    /**
     * @param emails of the users that you want to get the user object back
     *
     *               Returns UserObjects
     */
    private void getUsers(final List<String> emails) {
        database.getReference("cursisten").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot pDataSnapshot) {
                List<Cursist> cursisten = new ArrayList<>();
                if (pDataSnapshot.getChildren() != null) {
                    for (DataSnapshot snapshot : pDataSnapshot.getChildren()) {
                        Cursist value = snapshot.getValue(Cursist.class);
                        if (value == null || !emails.contains(value.getEmailadres()))
                            continue;
                        cursisten.add(value);
                    }
                }
                mView.onDataProvided(cursisten);
            }

            @Override
            public void onCancelled(final DatabaseError pDatabaseError) {

            }
        });
    }
}
