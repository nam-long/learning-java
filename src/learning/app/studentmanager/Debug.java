package learning.app.studentmanager;

class Debug {

    private static final boolean DEBUG = true;

    public static void i(String tag, String message) {

        if (DEBUG)
            System.out.println(String.format("%s>> %s", tag, message));
    }

    public static final void e(String tag, Exception e) {

        e(tag, e, "");
    }

    public static final void e(String tag, Exception e, String message) {

        if (DEBUG) {
            i(tag, message);
            e.printStackTrace();
        }
    }
}
