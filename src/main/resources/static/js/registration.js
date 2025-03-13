var app = new Vue({
    el: "#app",
    data: {
        name: "",
        userid: "",
        password: "",
    },
    methods: {
        signUp: function () {
            url = "http://localhost/api/user/addUser";
            console.log(url);
            fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    name: this.name,
                    userid: this.userid,
                    password: this.password,
                }),
            })
                .then((res) => res.json())
                .then((data) => {
                    if (data.message == "add success") {
                        window.location.href =
                            "http://localhost/api/home?uuid=" + data.uuid;
                    } else {
                        alert(data.message);
                    }
                    console.log(data);
                });
        },
        login: function () {
            window.location.href = "http://localhost/api/login";
        },
    },
    created: function () {},
});
