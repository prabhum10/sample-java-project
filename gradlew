#!/usr/bin/env sh
set -e

# Simple gradlew shim: runs installed `gradle` if present, otherwise instructs to generate wrapper files.
if command -v gradle >/dev/null 2>&1; then
  exec gradle "$@"
else
  echo "Gradle not found. To use the full Gradle Wrapper, generate it with: gradle wrapper"
  echo "Or install Gradle: https://gradle.org/install/"
  exit 1
fi
