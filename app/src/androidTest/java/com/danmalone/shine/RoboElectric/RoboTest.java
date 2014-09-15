package com.danmalone.shine.RoboElectric;

/**
 * Created by danmalone on 15/09/2014.
 */
import android.app.Activity;
import com.danmalone.shine.DayDetailActivity_;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;

@Config(manifest = "./src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class RoboTest {

    @Test
    public void testSomething() throws Exception {
        Activity activity = Robolectric.buildActivity(DayDetailActivity_.class).create().get();
        assertTrue(activity != null);
    }
}