function test(){
    console.log("test js");
}

// Example POST method implementation:
async function postData(url = "/device-test/api/auth/login", data = {username: "admin",password: "admin"}) {
  // Default options are marked with *
  const response = await fetch(url, {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    mode: "cors", // no-cors, *cors, same-origin
    cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
    credentials: "same-origin", // include, *same-origin, omit
    headers: {
      "Content-Type": "application/json",
    },
    redirect: "follow", // manual, *follow, error
    referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
    body: JSON.stringify(data), // body data type must match "Content-Type" header
  });
  return response.json(); // parses JSON response into native JavaScript objects
}

async function loginByForm(){
    const url = "/device-test/login"
    let formData = new FormData();
    formData.append("username","admin");
    formData.append("password","admin");

    const response = await fetch(url,{body: formData,method: "POST"})
    return response.json()
}