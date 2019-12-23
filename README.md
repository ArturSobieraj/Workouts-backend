# Workouts-backend
Backendowa aplikacja restowa dla mojej aplikacji frontendowej: https://github.com/ArturSobieraj/Workouts-frontend/commit/bcab1a378cec16995340c322ae4b0c8cc89270b1

Aplikacja pozwala na tworzenie i personalizację treningów na siłowni, z dostępem do bazy danych ćwiczeń.

## Uruchomienie:
- Należy utworzyć bazę danych MySql o nazwie "workouts_database" (localhost:3306)
- Należy utworzyć użytkownika "workout_user" z hasłem "password" i udzielić mu wszystkich uprawnień
- Po uruchomieniu apliacji backendowej i frondendowej, aplikacja znajduje się pod adresem http://localhost:8080

## Znane problemy:
W chwili wysłania commita zabrakło niestety czasu na: 
- Pokrycie testami większej ilości kodu
- Implementację 3 tabel, do których automatycznie zapisywane byłyby dane o ruchu na stronie
- Implementację controllerów odpowiedzialnych za dodanie i edycję treningu
- Refactor kodu