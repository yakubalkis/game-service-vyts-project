import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
const API_LOGIN_URL = "http://localhost:8080/api/v1/auth/login";

export default function Login(){

    const [labelWarning, setLabelWarning] = useState(""); // for basic warnings

    const [inputAll, setInputAll] = useState({ // for inputs in form
        username: "",
        password: ""
    });

    const navigate = useNavigate(); // to route the page


    function handleChange(event){ // get values from inputs
        const {name, value} = event.target;

        setInputAll(prevState => {
            return {
                ...prevState,
                [name]: value
            }
        });
    }

    function handleLogin(e){ // handle Login btn
        e.preventDefault();

        if(inputAll.username === "" || inputAll.password === ""){ // basic checks
            setLabelWarning(() => "Please fill in the all text boxes!");
            return;
        }
        else{setLabelWarning(() => "");}

        let userRequest = {username: inputAll.username, password: inputAll.password}; // create laborant request object to send backend

        axios.post(API_LOGIN_URL, userRequest) // request to login
            .then(res => {
                localStorage.setItem("tokenKey", res.data.message); // set items to local storage to use in other components
                localStorage.setItem("usernameOfCurrentUser", inputAll.username);
                if(res.data.role === "ROLE_USER") {
                    navigate(`/user/${inputAll.username}`); // route to user page !!!!!!!!!
                } else if(res.data.role === "ROLE_ADMIN") {
                    navigate(`/admin/${inputAll.username}`); // route to admin page !!!!!!!!!
                }
                window.location.reload();
            })
            .catch(res => setLabelWarning(() => "Invalid Username No or Password!")); // error message
    }

    return(
        <div className="row">
                <div className="col-md-6 col-md-offset-3">

                    <br></br>
                    <h1>User Login Page</h1>

                    <div className="card-body">
                        <form>

                            <div className="form-group">
                                <label>Username: </label>
                                <input className="form-control" placeholder="Username" type="text" name="username" value={inputAll.username}  onChange={handleChange}/>
                            </div><br></br>

                            <div className="form-group">
                                <label>Password: </label>
                                <input className="form-control" placeholder="Password" type="text" name="password" value={inputAll.password}  onChange={handleChange}/>
                            </div><br></br>
                            
                            <div className="form-group">

                                <button type="submit" className="btn btn-success" onClick={handleLogin}>Login</button>
                                <span style={{marginLeft: "1rem"}}>New user?
                                   <Link to="/">
                                        <span style={{marginLeft: "3px"}}>Sign Up</span>
                                   </Link>
                                </span>
                                
                            </div>

                            <label  style={{marginLeft: "0.5rem", color: "red"}}>{labelWarning}</label>

                        </form>
                    </div>

                </div>
            </div>
    )
}