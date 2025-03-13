var app = new Vue({
    el: "#app",
    data: {
        username: "",
        password: "",
    },
    methods: {
        //login function, using fetch api and send the data(userid and username) to the backend
        login: function () {
            url = "http://localhost/api/user/userlogin";
            console.log(url);
            fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    userid: this.username,
                    password: this.password,
                }),
            })
                .then((res) => res.json())
                .then((data) => {
                    //if the login is successful, redirect to the home page
                    if (data.message == "success") {
                        window.location.href =
                            "http://localhost/api/home?uuid=" + data.uuid;
                    }
                    console.log(data);
                });
        },
        register: function () {
            url = "http://localhost/api/registration";
            window.location.href = url;
        },
    },
    created: function () {},
});
