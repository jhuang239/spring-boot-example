var app = new Vue({
    el: "#app",
    data: {
        username: "",
        rows: [],
        renderRows: [],
        editRow: {
            id: 0,
            owner: "",
            title: "",
            description: "",
            stage: "Pending",
        },
        totalPage: 0,
        activePage: 1,
        mode: "",
        showCompleted: true,
        urlParames: new URLSearchParams(window.location.search),
    },
    methods: {
        //logout function, pass the uuid to the backend to delete the session
        logout: function () {
            url =
                "http://localhost/api/user/logout/" +
                this.urlParames.get("uuid");
            fetch(url, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
            })
                .then((res) => res.json())
                .then((data) => {
                    if (data.message == "logout success") {
                        window.location.href = "http://localhost/api/login";
                    }
                });
        },

        //change the active page and rerender the table
        changePage: function (page) {
            if (page < 1 || page > this.totalPage) return;
            this.activePage = page;
            this.renderRows = this.rows.slice((page - 1) * 5, page * 5);
        },
        // change the showCompleted status and rerender the table
        toggleShow: function () {
            this.showCompleted = !this.showCompleted;
            this.getData();
        },
        //get the data from the backend and assign it to rows, renderRows, totalPage
        getData: function () {
            this.username = document.getElementById("userid").innerHTML;
            let url =
                "http://localhost/api/todolist/getByUserID/" +
                this.urlParames.get("uuid");
            fetch(url, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
            })
                .then((res) => res.json())
                .then((data) => {
                    this.rows = data.filter((row) => {
                        if (this.showCompleted) {
                            return row;
                        } else {
                            return row.stage == "Pending";
                        }
                    });
                    this.totalPage = Math.ceil(this.rows.length / 5);

                    if (this.activePage > this.totalPage)
                        this.activePage = this.totalPage;
                    this.renderRows = this.rows.slice(
                        (this.activePage - 1) * 5,
                        this.activePage * 5
                    );
                    console.log(data);
                    console.log(this.totalPage);
                });
        },
        //delete the data using fetch api with delete method, and rerender the table
        deleteData: function (row) {
            let url =
                "http://localhost/api/todolist/deleteTodoList/" +
                this.urlParames.get("uuid");
            fetch(url, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    id: row.id,
                }),
            })
                .then((res) => res.json())
                .then((data) => {
                    this.getData();
                    console.log(data);
                });
        },
        //update the data using fetch api with post method, and rerender the table
        completeData: function (row) {
            let url =
                "http://localhost/api/todolist/updateTodoList/" +
                this.urlParames.get("uuid");
            fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    id: row.id,
                    owner: this.username,
                    title: row.title,
                    description: row.description,
                    stage: row.stage == "Pending" ? "Completed" : "Pending",
                }),
            })
                .then((res) => res.json())
                .then((data) => {
                    this.getData();
                    console.log(data);
                });
        },
        //set the editRow and mode to edit
        editData: function (row) {
            this.editRow = {
                id: row.id,
                owner: row.owner,
                title: row.title,
                description: row.description,
                stage: row.stage,
            };
            this.mode = "edit";
        },
        //set the editRow and mode to add
        addData: function () {
            this.editRow = {
                id: 0,
                owner: this.username,
                title: "",
                description: "",
                stage: "",
            };
            this.mode = "add";
        },
        //save the data using fetch api with post method, and rerender the table
        saveData: function () {
            let url = "";
            let body = {};
            //check the mode to decide the url and body
            if (this.mode == "add") {
                url =
                    "http://localhost/api/todolist/addTodoList/" +
                    this.urlParames.get("uuid");
                body = {
                    owner: this.username,
                    title: this.editRow.title,
                    description: this.editRow.description,
                    stage: "Pending",
                };
            } else if (this.mode == "edit") {
                url =
                    "http://localhost/api/todolist/updateTodoList/" +
                    this.urlParames.get("uuid");
                body = {
                    id: this.editRow.id,
                    owner: this.username,
                    title: this.editRow.title,
                    description: this.editRow.description,
                    stage: this.editRow.stage,
                };
            }
            fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(body),
            })
                .then((res) => res.json())
                .then((data) => {
                    this.editRow = {};
                    this.getData();
                    console.log(data);
                });
        },
    },
    created: function () {
        this.getData();
    },
});
