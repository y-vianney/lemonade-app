# Lemonade Application

*This project is a guided exercise designed to create a simple interactive mobile application using Android and Jetpack Compose. The goal is to implement a fun and intuitive app where users go through the process of making lemonade by interacting with visual elements.
Overview*

The Lemonade application allows users to:

- Select a lemon from a tree.
- Squeeze the lemon to make lemonade.
- Drink the lemonade.
- Restart the process by selecting a new lemon.

The app includes images and interactive labels at each step, creating a dynamic user experience.

## Features

**Interactive Steps**
    Tap a lemon tree to select a lemon.
    Press the lemon multiple times (randomized count between 2-4) to squeeze it.
    Tap the glass of lemonade to drink it.
    Tap the empty glass to start over.

**Randomization**
    The number of presses required to squeeze the lemon varies for each cycle, ensuring unique interactions.

**Visual Elements**
    Dynamic images and labels guide the user through each step.

## Getting Started
**Prerequisites**

    Android Studio (latest version recommended)
    Minimum SDK version: 24

**Project Setup**

-> _Create a New Project_
    Use the "Empty Activity" template.
    Project name: Lemonade
    Package name: com.example.lemonade

-> _Add Resources_
    Include the provided vector drawable files (lemon_tree.xml, lemon_squeeze.xml, lemon_drink.xml, lemon_restart.xml) into the res/drawable directory.
    Add the following strings to res/values/strings.xml:

    "Tap the lemon tree to select a lemon"
    "Keep tapping the lemon to squeeze it"
    "Tap the lemonade to drink it"
    "Tap the empty glass to start again"

## Implementation Steps

**Create the Layout**
    Design the UI for the lemon tree selection, squeezing, drinking, and restarting steps.
    Use Compose to dynamically change the image and label based on user interactions.

**Add Interaction Logic**
    Implement onClick listeners for transitioning between steps.
    Generate a random number (2-4) for lemon presses using Kotlin.

**Enhance the UI**
    Adjust font size and spacing for improved readability.
    Add a border and color accents to highlight interactive elements.
