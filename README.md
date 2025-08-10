
## 📽️ Movie Catalogue System

A dynamic web application built with **Java Spring Boot** and **Thymeleaf**, integrating with the [TMDb API](https://www.themoviedb.org/documentation/api) 
to display trending movies, search titles, filter by genre, and manage favorites—all in a responsive, user-friendly interface.




### 🚀 Features

- 🔥 **Trending Movies**: Displays weekly trending movies from TMDb.
- 🔍 **Search by Title**: Search for any movie using TMDb’s search endpoint.
- 📄 **Movie Details**: View full movie info including poster, overview, release date, and rating.
- ⭐ Star Ratings**: Visual star-based rating system based on TMDb scores.
- ❤️ **Favorites**: Add or remove movies from your favorites list.
- 📱 **Responsive Design**: Cards layout adapts beautifully across devices.




### 🛠️ Tech Stack

| Layer         | Technology             |
|---------------|------------------------|
| Backend       | Java 17, Spring Boot   |
| Frontend      | Thymeleaf, HTML/CSS    |
| API           | TMDb REST API          |
| Database      | H2 In-Memory DB        |
| Dependency Mgmt | Maven                |




### 📦 Project Structure


src/

├── _controller/       # Web controllers

├── _service/          # Business logic and API integration

├── _repository/       # JPA repository for favorites

├── _model/            # Movie and Genre models

├── templates/         # Thymeleaf HTML views

└── application.properties





### 📚 Credits

- [TMDb API](https://www.themoviedb.org/documentation/api)
- Font Awesome for icons
- Spring Boot & Thymeleaf for framework support

