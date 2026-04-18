const API = "http://localhost:8080/users";

document.getElementById("userForm").addEventListener("submit", function(e) {
    e.preventDefault();

    const user = {
        id: document.getElementById("userId").value,
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        mobile: document.getElementById("mobile").value,
        city: document.getElementById("city").value
    };

    if(user.id) {
        fetch(API + "/" + user.id, {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(user)
        }).then(loadUsers);
    } else {
        fetch(API, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(user)
        }).then(loadUsers);
    }

    document.getElementById("userForm").reset();
});

function loadUsers() {
    fetch(API)
    .then(res => res.json())
    .then(data => {
        let rows = "";
        data.forEach(u => {
            rows += `
            <tr>
                <td>${u.name}</td>
                <td>${u.email}</td>
                <td>${u.mobile}</td>
                <td>${u.city}</td>
                <td>
                    <button onclick="editUser(${u.id}, '${u.name}', '${u.email}', '${u.mobile}', '${u.city}')">Edit</button>
                    <button onclick="deleteUser(${u.id})">Delete</button>
                </td>
            </tr>`;
        });
        document.getElementById("userTable").innerHTML = rows;
    });
}

function editUser(id, name, email, mobile, city) {
    document.getElementById("userId").value = id;
    document.getElementById("name").value = name;
    document.getElementById("email").value = email;
    document.getElementById("mobile").value = mobile;
    document.getElementById("city").value = city;
}


function deleteUser(id) {
    fetch(API + "/" + id, { method: "DELETE" })
    .then(loadUsers);
}

loadUsers();