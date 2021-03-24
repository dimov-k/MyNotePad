package ru.mrroot.android1.notepad; //settings

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Calendar;

public class Note    {
        public static final String SHARED_PREFERENCE_NAME = "FragmentNavigation";
        public static final String IS_BACK_STACK_USED = "UseBackStack";
        public static final String IS_ADD_FRAGMENT_USED = "UseAddFragment";
        public static final String IS_BACK_AS_REMOVE_FRAGMENT = "BackAsRemove";
        public static final String IS_DELETE_FRAGMENT_BEFORE_ADD = "DeleteFragmentBeforeAdd";

        public static boolean IsBackStack;
        public static boolean IsAddFragment;
        public static boolean IsBackAsRemove;
        public static boolean IsDeleteBeforeAdd;
}