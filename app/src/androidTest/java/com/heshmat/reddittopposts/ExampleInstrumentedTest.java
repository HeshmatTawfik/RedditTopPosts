package com.heshmat.reddittopposts;

import android.content.Context;
import android.os.Parcel;

import com.heshmat.reddittopposts.models.Children;
import com.heshmat.reddittopposts.models.Data;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.heshmat.reddittopposts", appContext.getPackageName());
    }
    @Test
    public void testParcelable()  {
        Data data = new Data(false, "thumb", "author", "full name",
                10, 123456.0, 123456.0, "title", "permalink",
                "name", "before", "after", "ImgURl");
        Children myObject = new Children(data,"kind");

        Parcel parcel = Parcel.obtain();
        myObject.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        Children myObjectFromParcel = Children.CREATOR.createFromParcel(parcel);
        assertEquals(myObject, myObjectFromParcel);
    }
}
