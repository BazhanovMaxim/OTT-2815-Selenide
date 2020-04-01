# language:ru
@DeleteComments
Функционал: Удаление комментария
  Пользователь должен зайти на сайт
  Пользователь зайти на страницу записи и удалить комментарии (API и UI)
  Пользователь должен выйти из системы

  Сценарий: Удаление комментария
    Дано открыть сайт Jira
    Когда пользователь входит в систему
    Тогда открывается страница "System Dashboard"

    # Проверка через Status Code
    Тогда Удаляется комментарий через API

    # Удаление комментария через UI
    Когда пользователь нажимает на навигационную панель на Issue
    И пользователь нажимает на Reported By Me
    Тогда пользователь на странице "Reported by me"
    Когда пользовать выбирает запись по ключу
    И пользователь нажимает на кнопку Comments
    Тогда пользователь нажимает на кнопку удаления комментария
    И открывается окно "Delete Comment"
    Тогда пользователь нажимает на кнопку "Delete"
    Тогда проверяется удаление комментария

    И пользователь выходит из системы