# AI Chat Assistant Android App

Welcome to the AI Chat Assistant Android application, a powerful tool designed to provide a seamless conversational experience. This app is built with Kotlin, follows the MVVM architecture, employs Clean Architecture principles, utilizes Hilt for dependency injection, and boasts two main features: generating images and text answers via voice recognition.

## Features

1. **Voice Recognition:**
   - Leverage cutting-edge voice recognition technology to effortlessly interact with the AI chat assistant.

2. **Generate Images:**
   - Unlock the power of AI to dynamically generate images based on user inputs.

3. **Text Answers:**
   - Receive intelligent and context-aware text answers using advanced AI algorithms.

## Architecture

The project adheres to a clean architecture pattern to ensure modularity, scalability, and maintainability.

### Layers:

1. **Presentation Layer (MVVM):**
   - Hosts UI components, ViewModels, and LiveData.
   - Responsible for rendering data to users and managing user interactions.

2. **Domain Layer:**
   - Encompasses business logic and use cases.
   - Defines interfaces to be implemented by the data layer.

3. **Data Layer:**
   - Manages data from various sources, such as remote and local repositories.
   - Implements interfaces defined in the domain layer.

## Dependency Injection

Hilt is employed for dependency injection, offering a clean and standardized approach to handling dependencies.

## Project Structure

```plaintext
|-- app
    |-- src
        |-- main
            |-- java/com/example/aiassistant
                |-- di (Hilt modules)
                |-- presentation (MVVM components)
                |-- domain (Use cases)
                |-- data (Repositories and data sources)
|-- ...
```
## Getting Started
1. **Clone the Repository:**  
```bash
git clone https://github.com/your-username/ai-chat-assistant.git
```
3. **Open in Android Studio:**

Open the project in Android Studio.

5. **Build and Run**:

Build and run the application on your Android device (Real Device).
