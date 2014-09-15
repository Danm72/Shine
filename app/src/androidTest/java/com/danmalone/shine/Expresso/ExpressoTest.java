package com.danmalone.shine.Expresso;

        import android.test.ActivityInstrumentationTestCase2;
        import android.test.suitebuilder.annotation.LargeTest;

        import com.danmalone.shine.DayDetailActivity_;
        import com.danmalone.shine.DayListActivity_;
        import com.danmalone.shine.R;
        import com.danmalone.shine.R;

        import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
        import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
        import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
        import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;

@LargeTest
public class DayDetailActivityTest extends ActivityInstrumentationTestCase2<DayDetailActivity_> {

    @SuppressWarnings("deprecation")
    public DayDetailActivityTest() {
        // This constructor was deprecated - but we want to support lower API levels.
        super("com.danmalone.shine.daydetailactivity", DayDetailActivity_.class);
    }
    @Override
    public void setUp() throws Exception {
        super.setUp();
        // Espresso will not launch our activity for us, we must launch it via getActivity().
        getActivity();
    }

    public void testCheckText() {
        onView(withId(R.id.text))
                .check(matches(withText("Hello Espresso!")));
    }
}