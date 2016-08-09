/*
 * Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license.
 * See LICENSE in the project root for license information.
 */
package com.microsoft.graph.snippets;

import android.content.Intent;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import junit.framework.AssertionFailedError;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.Espresso.unregisterIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsAnything.anything;

@RunWith(AndroidJUnit4.class)
public class SnippetTests {
    @Rule
    public IntentsTestRule<SnippetListActivity> mSnippetListActivityRule = new IntentsTestRule<>(SnippetListActivity.class);
    @Rule
    public ActivityTestRule<SnippetDetailActivity> mSnippetDetailActivityRule = new ActivityTestRule<>(SnippetDetailActivity.class, false, false);

    @Test
    public void RunSnippets() throws InterruptedException{
        int numItems = ((ListView) mSnippetListActivityRule.getActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.snippet_list)
                .getView()
                .findViewById(android.R.id.list))
                .getAdapter()
                .getCount();

        List<Integer> snippetIndexes = new ArrayList<>();

        // Get the index of items in the adapter that
        // are actual snippets and not headers
        for (int i = 0; i < numItems; i++) {
            onData(anything())
                    .inAdapterView(withId(android.R.id.list))
                    .atPosition(i)
                    .perform(click());

            try {
                intended(allOf(
                        hasComponent(hasShortClassName(".SnippetDetailActivity")),
                        hasExtra("item_id", i),
                        toPackage("com.microsoft.graph.snippets")
                ));
                snippetIndexes.add(i);
                onView(withContentDescription("Navigate up")).perform(click());
            } catch (AssertionFailedError e) {
                continue;
            }
        }

        // Execute the snippets
        for(int index : snippetIndexes) {
            Intent itemIdIntent = new Intent();
            itemIdIntent.putExtra("item_id", index);
            SnippetDetailActivity snippetDetailActivity = mSnippetDetailActivityRule.launchActivity(itemIdIntent);

            SnippetDetailActivityIdlingResource idlingResource = new SnippetDetailActivityIdlingResource(snippetDetailActivity);
            registerIdlingResources(idlingResource);

            onView(withId(R.id.btn_run)).perform(click());

            onView(withId(R.id.txt_status_color)).check(matches(withContentDescription(R.string.stoplight_success)));
            unregisterIdlingResources(idlingResource);
            snippetDetailActivity.finish();
        }
    }

    private class SnippetDetailActivityIdlingResource implements IdlingResource {
        private SnippetDetailActivity activity;
        private ResourceCallback callback;

        public SnippetDetailActivityIdlingResource(SnippetDetailActivity activity) {
            this.activity = activity;
        }

        @Override
        public String getName() {
            return this.getClass().getName();
        }

        @Override
        public boolean isIdleNow() {
            Boolean idle = activity != null &&
                    callback != null &&
                    !activity.isExecutingRequest();
            if (idle) callback.onTransitionToIdle();
            return idle;
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
            this.callback = resourceCallback;
        }
    }
}
