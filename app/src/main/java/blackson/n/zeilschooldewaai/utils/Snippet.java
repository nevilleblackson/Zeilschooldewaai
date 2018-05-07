package blackson.n.zeilschooldewaai.utils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import blackson.n.zeilschooldewaai.interfaces.SuccessListener;
import blackson.n.zeilschooldewaai.models.CursistCursus;
import blackson.n.zeilschooldewaai.models.Cursus;
import blackson.n.zeilschooldewaai.models.SoortenCursus;

public class Snippet {

    public static void CursesEmpty(final SuccessListener<Boolean> pListener, String cursesId) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference("soortenCursussen").child(cursesId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot pDataSnapshot) {
                SoortenCursus cursus = pDataSnapshot.getValue(SoortenCursus.class);

                database.getReference("cursus").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot pDataSnapshot) {
                        final List<Long> cursuscodes = new ArrayList<>();

                        if (pDataSnapshot.getChildren() != null) {
                            for (DataSnapshot snapshot : pDataSnapshot.getChildren()) {
                                Cursus cursus = snapshot.getValue(Cursus.class);
                                if (cursus == null)
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
                                pListener.onSuccess(count == 0);
                            }

                            @Override
                            public void onCancelled(final DatabaseError pDatabaseError) {
                                pListener.onFailure(pDatabaseError.getDetails());

                            }
                        });
                    }

                    @Override
                    public void onCancelled(final DatabaseError pDatabaseError) {
                        pListener.onFailure(pDatabaseError.getDetails());

                    }
                });
            }

            @Override
            public void onCancelled(final DatabaseError pDatabaseError) {
                pListener.onFailure(pDatabaseError.getDetails());

            }
        });
    }
}
