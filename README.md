# Ex23_SharedPreferences

Lecture 06 - Local Storage

The app displays a single screen with a help message pointing the user to the options menu to show
or hide an initial welcome dialog when the app starts.
The user's preferences are stored in local storage using the SharedPreferences library.

- The initial welcome dialog will be displayed according to the user's preferences. This is obtained
  as a single value from SharedPreferences. It presents a Switch to let the user change her preferences
  from this dialog.
- The options menu lets the users change her preferences and it is updated accordingly. Action
  elements are continuously updated according to a Flow provided by SharedPreferences.
- All operations have been moved to an optimised IO thread.
