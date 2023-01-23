package de.nulide.shiftcal;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestSuite {

    @Rule
    public ActivityScenarioRule<CalendarActivity> activityScenarioRule = new ActivityScenarioRule<>(CalendarActivity.class);

    /**
     * The application is a calendar for managing your employers and shifts.
     */

    UiDevice device = UiDevice.getInstance(getInstrumentation());

    public void gotoEmployer() {
        onView(withId(R.id.btnPopup)).perform(click());
        onView(withText("Employers")).perform(click());
    }

    public void createEmployerSheffield() {
        gotoEmployer();
        onView(withId(R.id.fabAddEmployer)).perform(click());
        onView(withId(R.id.scEditTextName)).perform(typeText("Sheffield"));
        device.pressEnter();
        onView(withId(R.id.fabDoneEmployer)).perform(click());
    }

    public void deleteEmployerSheffield() {
        onView(withId(R.id.textViewName)).perform(longClick());
        onView(withText("Delete")).perform(click());
        pressBack();
    }

    public void gotoShift() {
        onView(withId(R.id.btnPopup)).perform(click());
        onView(withText("Shifts")).perform(click());
    }

    public void createShiftWeekday() {
        gotoShift();
        onView(withId(R.id.fabAddShift)).perform(click());
        onView(withId(R.id.scEditTextName)).perform(typeText("Weekday"));
        onView(withId(R.id.scEditTextSName)).perform(typeText("WD"));
        onView(withId(R.id.btnStartTime)).perform(click());
        device.wait(Until.findObject(By.res("android:id/time_header")), 5000);
        device.findObject(By.desc("9")).click();
        device.wait(Until.findObject(By.desc("30")), 5000);
        device.findObject(By.text("OK")).click();
        onView(withId(R.id.btnEndTime)).perform(click());
        device.wait(Until.findObject(By.res("android:id/time_header")), 5000);
        device.findObject(By.desc("17")).click();
        device.wait(Until.findObject(By.desc("30")), 5000);
        device.findObject(By.text("OK")).click();
        onView(withId(R.id.fabDoneShift)).perform(click());
    }

    public void deleteShiftWeekday() {
        onView(withId(R.id.textViewSName)).perform(longClick());
        onView(withText("Delete")).perform(click());
        pressBack();
    }

    @Test
    public void createEmployer() {
        // 1. Click the menu icon in the top-right corner.
        // 2. Click "Employers".
        // 3. Click the + icon on the bottom-right corner.
        // 4. Enter "Sheffield" in the Name field (then press enter on the keyboard).
        // 5. Click the ✓ icon.
        createEmployerSheffield();
        // 6. Long click "Sheffield".
        // 7. Click "Delete".
        // 8. Press back to return to the homepage.
        deleteEmployerSheffield();
    }

    @Test
    public void createShifts() {
        // 1. Create Employer "Sheffield" (steps 1 - 5 in test case createEmployer).
        createEmployerSheffield();
        // 2. Press back to return to the homepage.
        pressBack();
        // 3. Click the menu icon in the top-right corner.
        // 4. Click "Shifts".
        // 5. Click the + icon on the bottom-right corner.
        // 6. Enter "Weekday" in the Name field (then press enter on the keyboard).
        // 7. Enter "WD" in the Short Name field (then press enter on the keyboard).
        // 8. Click the button of Start Time, click 9 then click OK.
        // 9. Click the button of End Time, click 17 then click OK.
        // 10. Click the ✓ icon.
        createShiftWeekday();
        // 11. Long click "Weekday".
        // 12. Click "Delete".
        // 13. Press back to return to the homepage.
        deleteShiftWeekday();
        // 14. Go to the Employers page (steps 1 - 2 in test case createEmployer).
        gotoEmployer();
        // 15. Delete Employer "Sheffield" then return to the homepage (steps 6 - 8 in test case createEmployer)
        deleteEmployerSheffield();
    }

    @Test
    public void setShifts() {
        // 1. Create Employer "Sheffield" and Shift "Weekday" (steps 1 - 10 in test case createShifts).
        createEmployerSheffield();
        pressBack();
        createShiftWeekday();
        // 2. Press back to return to the homepage.
        pressBack();
        // 3. Click the Edit icon in the bottom-right corner of the homepage.
        onView(withId(R.id.fabEdit)).perform(click());
        // 4. Click the S icon.
        onView(withId(R.id.fabShiftSelector)).perform(click());
        // 5. Click "Weekday".
        onView(withText("Weekday")).perform(click());
        // 6. Click date 30 of January 2023.
        device.findObject(By.desc("30")).click();
        // 7. Click the ✓ icon.
        onView(withId(R.id.fabEdit)).perform(click());
        // 8. Click the Edit icon in the bottom-right corner of the homepage.
        onView(withId(R.id.fabEdit)).perform(click());
        // 9. Click the WD icon.
        onView(withId(R.id.fabShiftSelector)).perform(click());
        // 10. Click "Delete".
        onView(withText("Delete")).perform(click());
        // 11. Click date 30 of January 2023.
        device.findObject(By.desc("30")).click();
        // 12. Click the ✓ icon.
        onView(withId(R.id.fabEdit)).perform(click());
        // 13. Go to the Shifts page (steps 3 - 4 in test case createShifts).
        gotoShift();
        // 14. Delete Shift "Weekday" and Employer "Sheffield" then return to the homepage (steps 11 - 15 in test case createShifts).
        deleteShiftWeekday();
        gotoEmployer();
        deleteEmployerSheffield();
    }
}
