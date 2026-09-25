# Tictronome

A metronome for Wear OS that vibrates on the beat. Built with Kotlin and Compose for Wear OS.

## Features

- **Set a BPM** — adjust with +/− buttons and start
- **Tap tempo** — tap the screen in time and Tictronome detects the BPM for you
- **Vibration beat** — feels the pulse on your wrist, no sound needed
- **Standalone** — runs entirely on the watch, no phone required

## Requirements

- Wear OS 3+ watch (API 31+)
- Vibration hardware (haptic motor)

## Install

Download the latest signed `app-release.apk` from [GitHub Releases](https://github.com/diegosoriarios/Tictronome/releases), then install it with ADB:

```bash
adb install app-release.apk
```

## Build from source

```bash
./gradlew assembleDebug
```

Or open the project in Android Studio with the Wear OS emulator/device available.

Release builds are signed in CI when `KEYSTORE`, `SIGNING_STORE_PASSWORD` and (optionally) `SIGNING_KEY_ALIAS` secrets are set; locally, release builds fall back to the debug signing config so `./gradlew assembleRelease` always works.

## Screenshots

_Coming soon_

## Roadmap

See [IMPROVEMENTS_TODO.md](IMPROVEMENTS_TODO.md) for planned improvements (MVVM refactor, tests, BPM range validation, audio metronome, and more).
