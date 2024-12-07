// Массив для хранения записей
let notes = [];

// Получение ссылок на элементы DOM
const notesList = document.getElementById('notes-list');
const noteContent = document.getElementById('note-content');

/**
 * Функция для рендеринга списка записей
 */
function renderNotes() {
    // Очищаем список записей перед рендерингом
    notesList.innerHTML = '';

    // Перебираем массив записей и создаем пункты меню
    notes.forEach((note, index) => {
        let li = document.createElement('li');
        li.textContent = `Запись ${index + 1}`; // Текст пункта меню
        li.dataset.index = index;               // Сохраняем индекс записи в data-атрибуте
        li.onclick = () => loadNote(index);     // Назначаем обработчик события click
        notesList.appendChild(li);              // Добавляем пункт в список
    });
}

/**
 * Функция для сохранения текущей записи
 * @param {number|null} index Индекс записи в массиве или null для новой записи
 */
function saveNote(index) {
    if (index === null) {
        // Если index равен null, то создаем новую запись
        notes.push(noteContent.value);
    } else {
        // Иначе обновляем существующую запись
        notes[index] = noteContent.value;
    }
    renderNotes(); // Рендерим список записей заново
}

/**
 * Функция для загрузки записи в текстовое поле
 * @param {number} index Индекс записи в массиве
 */
function loadNote(index) {
    noteContent.value = notes[index]; // Устанавливаем значение текстового поля
}

/**
 * Функция для создания новой записи
 */
function createNewNote() {
    noteContent.value = ''; // Очищаем текстовое поле
    saveNote(null);        // Сохраняем новую запись
}

/**
 * Функция для редактирования существующей записи
 * @param {number} index Индекс записи в массиве
 */
function editNote(index) {
    noteContent.value = notes[index]; // Загружаем текст записи в текстовое поле
    saveNote(index);                 // Сохраняем изменения
}

// Первоначальный рендеринг списка записей
renderNotes();
