function deleteUsers() {
    const checkedCheckBoxes = document.querySelectorAll('input[name=userId]:checked');
    if (checkedCheckBoxes.length === 0) {
        alert("Пользователи не выбраны");
        return;
    }
    let ids = "";

    for (let i = 0; i < checkedCheckBoxes.length; i++) {
        ids = ids + checkedCheckBoxes[i].value + " ";
    }

    document.getElementById("idsForDelete").value = ids;
    document.getElementById("deleteForm").submit();
}

function modifyUser() {
    const checkedCheckBoxes = document.querySelectorAll('input[name=userId]:checked');
    if (checkedCheckBoxes.length !== 1) {
        alert("Нужно выбрать одного пользователя");
        return;
    }
    document.getElementById("idForModify").value = checkedCheckBoxes[0].value;
    document.getElementById("modifyForm").submit();
}

function userPosts() {
    const checkedCheckBoxes = document.querySelectorAll('input[name=userId]:checked');
    if (checkedCheckBoxes.length !== 1) {
        alert("Нужно выбрать одного пользователя");
        return;
    }

    document.getElementById("idForPosts").value = checkedCheckBoxes[0].value;
    document.getElementById("postsForm").submit();
}