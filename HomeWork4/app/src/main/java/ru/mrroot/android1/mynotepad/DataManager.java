package ru.mrroot.android1.mynotepad;

import java.util.ArrayList;
import java.util.List;


public class DataManager {
    private static DataManager ourInstance = null;

    private List<CourseInfo> mCourses = new ArrayList<>();
    private List<NoteInfo> mNotes = new ArrayList<>();

    public static DataManager getInstance() {
        if(ourInstance == null) {
            ourInstance = new DataManager();
            ourInstance.initializeCourses();
            ourInstance.initializeExampleNotes();
        }
        return ourInstance;
    }

    public String getCurrentUserName() {
        return "Jim Wilson";
    }

    public String getCurrentUserEmail() {
        return "jimw@jwhh.com";
    }

    public List<NoteInfo> getNotes() {
        return mNotes;
    }

    public int createNewNote() {
        NoteInfo note = new NoteInfo(null, null, null);
        mNotes.add(note);
        return mNotes.size() - 1;
    }

    public int findNote(NoteInfo note) {
        for(int index = 0; index < mNotes.size(); index++) {
            if(note.equals(mNotes.get(index)))
                return index;
        }

        return -1;
    }

    public void removeNote(int index) {
        mNotes.remove(index);
    }

    public List<CourseInfo> getCourses() {
        return mCourses;
    }

    public CourseInfo getCourse(String id) {
        for (CourseInfo course : mCourses) {
            if (id.equals(course.getCourseId()))
                return course;
        }
        return null;
    }

    public List<NoteInfo> getNotes(CourseInfo course) {
        ArrayList<NoteInfo> notes = new ArrayList<>();
        for(NoteInfo note:mNotes) {
            if(course.equals(note.getCourse()))
                notes.add(note);
        }
        return notes;
    }

    public int getNoteCount(CourseInfo course) {
        int count = 0;
        for(NoteInfo note:mNotes) {
            if(course.equals(note.getCourse()))
                count++;
        }
        return count;
    }

    private DataManager() {
    }


    private void initializeCourses() {
        mCourses.add(initializeCourse1());
        mCourses.add(initializeCourse2());
        mCourses.add(initializeCourse3());
        mCourses.add(initializeCourse4());
    }

    public void initializeExampleNotes() {
        final DataManager dm = getInstance();

        CourseInfo course = dm.getCourse("example");
        course.getModule("example_m01").setComplete(true);

        mNotes.add(new NoteInfo(course, "Покупки",
                "Хлеб, молоко, кефир"));
        mNotes.add(new NoteInfo(course, "Покупки на неделю",
                "Мясо, рыба, мангал, угли"));

        course = dm.getCourse("example2");
        course.getModule("example2_m01").setComplete(true);

        mNotes.add(new NoteInfo(course, "Login/Passwd",
                "Administrator/Password1452"));
        mNotes.add(new NoteInfo(course, "Ip server",
                "192.168.33.33"));

        course = dm.getCourse("example3");
        course.getModule("example3_m01").setComplete(true);

        mNotes.add(new NoteInfo(course, "Русский Рок",
                "Алиса, Агата кристи, сектор газа, сплин"));
        mNotes.add(new NoteInfo(course, "American Rock",
                "Metallica, Linkin Park\n, Queen"));

        course = dm.getCourse("example4");
        course.getModule("example4_m01").setComplete(true);
        mNotes.add(new NoteInfo(course, "Рецепт крем супа",
                "Нарежьте грибы тонкими ломтиками. Выложите их в сотейник, добавьте воду, бульонные кубики и измельчённый лук и доведите до кипения. Убавьте огонь, накройте крышкой и варите ещё 20 минут.\n" +
                        "\n" +
                        "В другом сотейнике или кастрюле растопите масло на среднем огне. Добавьте муку и перемешайте."));
        mNotes.add(new NoteInfo(course, "Рецепт маринада",
                "Количество овощей я не указываю, могу сказать только, что маринада мне хватило бы на 1,5 порции, а у меня было: один кабачок, баклажан, лук и перец. И немного (грамм 300) шампиньонов.\n" +
                        "\n" +
                        "Овощи готовы! Сочные, яркие и очень вкусные!"));
    }

    private CourseInfo initializeCourse1() {
        List<ModuleInfo> modules = new ArrayList<>();
        modules.add(new ModuleInfo("example_m01", "Пример_1"));

        return new CourseInfo("example", "Первый пример", modules);
    }

    private CourseInfo initializeCourse2() {
        List<ModuleInfo> modules = new ArrayList<>();
        modules.add(new ModuleInfo("example2_m01", "example2_1"));

        return new CourseInfo("example2", "Work_everyday", modules);
    }

    private CourseInfo initializeCourse3() {
        List<ModuleInfo> modules = new ArrayList<>();
        modules.add(new ModuleInfo("example3_m01", "example3_1"));

        return new CourseInfo("example3", "Песни", modules);
    }

    private CourseInfo initializeCourse4() {
        List<ModuleInfo> modules = new ArrayList<>();
        modules.add(new ModuleInfo("example4_m01", "example4_1"));

        return new CourseInfo("example4", "Рецепты", modules);
    }

    public int createNewNote(CourseInfo course, String noteTitle, String noteText) {
        int index = createNewNote();
        NoteInfo note = getNotes().get(index);
        note.setCourse(course);
        note.setTitle(noteTitle);
        note.setText(noteText);

        return index;
    }

}
