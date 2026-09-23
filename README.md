# Notepad

A simple Android note-taking app built with **Kotlin** and **Jetpack Compose**, created as a learning project to understand **Navigation Compose**, shared **ViewModel** state, and Material 3 UI patterns.

## Features

- View saved notes as cards in a scrollable list
- Add a new note via a floating action button
- Tap an existing note to open and edit it, with fields pre-filled
- Notes persist in shared app state while the app is running

## Architecture

MainActivity
└── NavHost (navController, screenViewModel)
├── NotePage  (note list — Routes.screenA)
└── NoteList  (add / edit a note — Routes.screenB/{noteIndex})

- **`ScreenViewModel`** — holds the list of notes in a `MutableStateFlow`, exposed as a read-only `StateFlow`. Created once in `MainActivity` and passed down to both screens, so they always observe the same data.
- **`ScreenUiState`** — the data class representing the screen's state (the list of notes).
- **`NotePad`** (in `ScreenList.kt`) — the data class for a single note (`title`, `entry`).
- **`Routes`** — defines the navigation routes. `screenB` accepts a `noteIndex` argument: `-1` means "creating a new note," any value `0` or above means "editing the note at that position in the list."

## Navigation flow

1. `NotePage` displays notes as cards in a `LazyColumn`, inside a `Scaffold` with a `TopAppBar` and `FloatingActionButton`.
2. Tapping the FAB navigates to `NoteList` with `noteIndex = -1` (new note).
3. Tapping an existing note navigates to `NoteList` with that note's index (edit mode) — the screen pre-fills the title and text fields with the existing note's content.
4. Saving on `NoteList` either appends a new note or updates the existing one in the shared `ViewModel`, then navigates back to `NotePage`, which automatically recomposes to show the change.

## Tech stack

- Kotlin
- Jetpack Compose (Material 3 — Scaffold, TopAppBar, FloatingActionButton)
- Navigation Compose
- ViewModel + StateFlow

## Running the project

1. Clone the repo
2. Open in Android Studio
3. Run on an emulator or physical device (minimum SDK as configured in `app/build.gradle.kts`)

## Status

Core navigation, editing, and UI polish (Scaffold, theming, empty states) are complete. Next planned features: delete a note, save validation, and persistent storage (Room) so notes survive an app restart.