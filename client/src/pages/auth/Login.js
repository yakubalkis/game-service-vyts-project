import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import googleLogo from '../../img/google-logo.png';
import githubLogo from '../../img/github-logo.png';
import images from '../../img/images1.png';
import { getSocialLoginUrl } from "../../utils/Helpers";
const API_LOGIN_URL = "http://localhost:8080/auth/login";

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
                localStorage.setItem("user", JSON.stringify(inputAll.username));
                if(res.data.role === "ROLE_USER") {
                    navigate(`/user/${inputAll.username}`); // route to user page !!!!!!!!!
                } else if(res.data.role === "ROLE_ADMIN") {
                    navigate(`/admin/${inputAll.username}`); // route to admin page !!!!!!!!!
                }
                window.location.reload();
            })
            .catch(res => setLabelWarning(() => "Invalid Username No or Password!")); // error message
    }

    return (
        <div className="container d-flex justify-content-center align-items-center min-vh-100">
            <div className="row border rounded-5 p-3 bg-white shadow box-area">
                <div className="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box">
                    <div className="featured-image mb-3">
                        <img src={images}  className="img-fluid" style={{ width: '250px' }} alt="Featured" />
                    </div>
                    <p className="text-white fs-2" style={{ fontFamily: 'Courier New', fontWeight: 600 }}>Be Verified</p>
                    <small className="text-white text-wrap text-center" style={{ width: '17rem', fontFamily: 'Courier New' }}>
                        Join experienced Designers on this platform.
                    </small>
                </div>
                <div className="col-md-6 right-box">
                    <div className="row align-items-center">
                        <div className="header-text mb-4">
                            <h2>Hello, Again</h2>
                            <p>We are happy to have you back.</p>
                        </div>
                        <form>
                            <div className="form-group">
                                <label>Username: </label>
                                <input className="form-control form-control-lg bg-light fs-6" placeholder="Username" type="text" name="username" value={inputAll.username} onChange={handleChange} />
                            </div>
                            <br />
                            <div className="form-group">
                                <label>Password: </label>
                                <input className="form-control form-control-lg bg-light fs-6" placeholder="Password" type="password" name="password" value={inputAll.password} onChange={handleChange} />
                            </div>
                            <br />
                            
                            <div className="form-group">
                                <button type="submit" className="btn btn-lg btn-primary w-100 fs-6" onClick={handleLogin}>Login</button>
                            </div>
                        
                            <div className="row">
                                <small>Don't have an account? <Link to="/">Sign Up</Link></small>
                            </div>
                            <label style={{ marginLeft: '0.5rem', color: 'red' }}>{labelWarning}</label>
                        </form>
                        <div className="social-login">
                        <div className="row">
                                <div className="col-2">
                                    <a className="btn social-btn google" href={getSocialLoginUrl('google')}>
                                        <img src={googleLogo} alt="Google" style={{ height: '30px' }} />
                                    </a>
                                </div>
                                <div className="col-10 d-flex align-items-center">
                                    <label style={{ marginBottom: '0' }}>Log in with Google</label>
                                </div>
                            </div>
                            <div className="row mt-2"> {/* Added margin-top for better alignment */}
                            <div className="col-2">
                                <a className="btn social-btn github" href={getSocialLoginUrl('github')}>
                                    <img src={githubLogo} alt="Github" style={{ height: '30px' }} />
                                </a>
                            </div>
                            <div className="col-10 d-flex align-items-center">
                                <label style={{ marginBottom: '0' }}>Log in with Github</label>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}