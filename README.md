# Notepad

A simple Android note-taking app built with **Kotlin** and **Jetpack Compose**, created as a learning project to understand **Navigation Compose** and shared **ViewModel** state across screens.

## Features

- View a list of saved notes on the home screen
- Add a new note
- Tap an existing note to open and edit it
- Notes persist in shared app state while the app is running

## Architecture

The app uses a simple two-screen navigation flow with a single shared `ViewModel`:

```
MainActivity
 └── NavHost (navController, screenViewModel)
      ├── NoteList  (note list)
      └── NotePage  (add / edit a note)
```

- **`ScreenViewModel`** — holds the list of notes in a `MutableStateFlow`, exposed as a read-only `StateFlow`. It's created once in `MainActivity` and passed down to both screens, so they always observe the same data.
- **`ScreenUiState`** — the data class representing the screen's state (currently just the list of notes).
- **`NotePad`** — the data class for a single note (`title`, `entry`).
- **`Routes`** — defines the navigation routes. `NotePage` accepts an optional `noteIndex` argument: `-1` means "creating a new note," any value `0` or above means "editing the note at that position in the list."

## Navigation flow

1. `NoteList` displays notes as cards in a `LazyColumn`.
2. Tapping the **+** button navigates to `NotePage` with `noteIndex = -1` (new note).
3. Tapping an existing note navigates to `NotePage` with that note's index (edit mode) — the screen pre-fills the title and text fields with the existing note's content.
4. Saving on `NotePage` either appends a new note or updates the existing one in the shared `ViewModel`, then navigates back to `NotePage`, which automatically recomposes to show the change.

## Tech stack

- Kotlin
- Jetpack Compose (Material 3)
- Navigation Compose
- ViewModel + StateFlow

## Running the project

1. Clone the repo
2. Open in Android Studio
3. Run on an emulator or physical device (minimum SDK as configured in `app/build.gradle.kts`)

## Status

Work in progress — built as part of learning Jetpack Compose Navigation. Next planned feature: deleting a note.