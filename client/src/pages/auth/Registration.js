import { useState } from "react"
import axios from "axios";
import { Link } from "react-router-dom";
import MessageToast from "../../components/MessageToast";
const API_BASE_URL = "http://localhost:8080/auth/register";

export default function Registration() {

    
    const [show, setShow] = useState(false); // is used to show MessageToast component, will be passed to MessageToast as prop
    const [message, setMessage] = useState(""); // is also used for message in MessageToast component
    const [labelWarning, setLabelWarning] = useState(""); // basic warnings for user
    const [roleType, setRoleType] = useState(""); // role type (user/admin)
 
    const [inputAll, setInputAll] = useState({ // for input values in form
        username: "",
        email: "",
        phoneNumber: "",
        password: ""
    }); 

    function handleOption(event){ // is used for radio buttons 
        setRoleType(event.target.value)
    }

    function handleChange(event){ // is used to get values from inputs
        const {name, value} = event.target;

        setInputAll(prevState => {
            return {
                ...prevState,
                [name]: value
            }
        });
    }

    function saveUser(e){ // to register user
        e.preventDefault();
        
        if(inputAll.email === "" || inputAll.username === "" || inputAll.password === "" || inputAll.phoneNumber === ""){ // basic checks
            setLabelWarning(() => "Please fill in the all text boxes!");
            return;
        }
        else if(roleType === ""){
            setLabelWarning(() => "Please choose your role!");
            return;
        }
        else{setLabelWarning(() => "");}

        let userRegister = {username: inputAll.username, email:inputAll.email, phoneNumber: inputAll.phoneNumber, password: inputAll.password}; // create user object to send to backend
        
        
        axios.post(API_BASE_URL +"/"+ roleType, userRegister) // request to save user
            .then(res => {
                if(res.status === 201){
                   setMessage(res.data.message); // show message when registration is successful
                   setShow(true);
                   setRoleType("");
                   setInputAll({ // clean up form inputs
                   username: "",
                   email:"",
                   phoneNumber: "",
                   password: ""                   
                   });
                }
            }).catch(res => {
                   setMessage(res.response.data.message); // Username must be UNIQUE, otherwise will give error and show error message
                   setShow(true);
            })
    }

    return(
        
            <div className="row">
                <div className="col-md-6 col-md-offset-3">

                    <br></br>
                    <h1>User Registration Page</h1>

                    <div className="card-body">
                        <form>
                            <div className="form-group">
                                <label>Username</label>
                                <input className="form-control" placeholder="Username" type="text" name="username" value={inputAll.username}  onChange={handleChange} />
                            </div><br></br>

                            <div className="form-group">
                                <label>Email</label>
                                <input className="form-control" placeholder="Email" type="text" name="email" value={inputAll.email}  onChange={handleChange} />
                            </div><br></br>

                            <div className="form-group">
                                <label>Phone Number: </label>
                                <input className="form-control" placeholder="Phone Number" type="text" name="phoneNumber" value={inputAll.phoneNumber}  onChange={handleChange}/>
                            </div><br></br>

                            <div className="form-group">
                                <label>Password: </label>
                                <input className="form-control" placeholder="Password" type="text" name="password" value={inputAll.password}  onChange={handleChange}/>
                            </div><br></br>

                            <div>
                                <label>Choose your role:</label><br></br>
                                <input className="form-check-input" type="radio" name="role" value="user"  onChange={handleOption} checked={roleType==="user"} />
                                    <label style={{marginLeft:".2rem"}}>Normal User</label>
                                <input className="form-check-input" style={{marginLeft:"1rem"}} type="radio" name="role" value="admin" onChange={handleOption} checked={roleType==="admin"}/>
                                    <label style={{marginLeft:".2rem"}}>Admin</label>
                            </div><br></br>
                            
                            <div className="form-group">
                                <button type="submit" className="btn btn-success" onClick={saveUser}>Register</button>
                                <span style={{marginLeft: "1rem"}}>Already registered?
                                   <Link to="/login">
                                        <span style={{marginLeft: "3px"}}>Login</span>
                                   </Link>
                                </span>
                            </div>

                            <label  style={{color: "red"}}>{labelWarning}</label>

                        </form>
                    </div>

                </div>
                <div style={{ position: 'fixed', left: 0, bottom: 0 }}>
                        <MessageToast show={show} message={message} setShow={setShow} />
                </div>
            </div>
        
    )

}