package blackson.n.zeilschooldewaai.presenters;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import blackson.n.zeilschooldewaai.fragments.views.CursesOverviewView;
import blackson.n.zeilschooldewaai.interfaces.BackgroundListener;
import blackson.n.zeilschooldewaai.models.CursistCursus;
import blackson.n.zeilschooldewaai.models.Cursus;
import blackson.n.zeilschooldewaai.models.SoortenCursus;

public class CursesOverviewPresenter extends BasePresenter<CursesOverviewView> {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    public CursesOverviewPresenter(final CursesOverviewView pView) {
        super(pView);
    }

    /**
     * @param month when the course starts
     * @param year  when the course starts
     */
    public void provide(final int month, final int year) {
        database.getReference("soortenCursussen").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot pDataSnapshot) {
                final List<SoortenCursus> soortenCursussen = new ArrayList<>();
                if (pDataSnapshot.getChildren() != null)
                    for (DataSnapshot snapshot : pDataSnapshot.getChildren()) {
                        SoortenCursus cursus = snapshot.getValue(SoortenCursus.class);
                        if (cursus == null)
                            continue;
                        soortenCursussen.add(cursus);
                    }

                for (final SoortenCursus cursus : soortenCursussen) {
                    getUserCount(year, month, cursus, new BackgroundListener<Integer>() {
                        @Override
                        public void finished(final Integer count) {
                            // price * amount of users that follow the course  =  the "opbrengsten"
                            cursus.setTotal(cursus.getPrijs() * count);
                            mView.addItem(cursus);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(final DatabaseError pDatabaseError) {
                // the call failed show the user a message
                mView.onFailure(pDatabaseError.getDetails());
            }
        });
    }

    /**
     * returns the amount of users that are following a course
     */
    private void getUserCount(final int year, final int month, final SoortenCursus pCursus, final BackgroundListener<Integer> pListener) {

        database.getReference("cursus").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot pDataSnapshot) {
                final List<Long> cursuscodes = new ArrayList<>();

                if (pDataSnapshot.getChildren() != null) {
                    for (DataSnapshot snapshot : pDataSnapshot.getChildren()) {
                        Cursus cursus = snapshot.getValue(Cursus.class);
                        if (cursus == null)
                            continue;
                        Calendar calendar = Calendar.getInstance();
                        Calendar.getInstance().setTime(new Date(cursus.getBegindatum()));
                        if (calendar.get(Calendar.MONTH) != month || calendar.get(Calendar.YEAR) != year || !cursus.getSoortcode().equals(pCursus.getSoortcode()))
                            continue;
                        cursuscodes.add(cursus.getCursuscode());
                    }
                }

                database.getReference("cursistCursus").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot pDataSnapshot) {
                        int count = 0;
                        if (pDataSnapshot.getChildren() != null) {
                            for (DataSnapshot snapshot : pDataSnapshot.getChildren()) {
                                CursistCursus value = snapshot.getValue(CursistCursus.class);
                                if (value == null || !cursuscodes.contains(value.getCursusCode()))
                                    continue;
                                // user found add 1 to the count
                                count++;
                            }
                        }
                        pListener.finished(count);
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
}