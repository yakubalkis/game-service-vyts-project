import { useState } from "react"
import axios from "axios";
import { Link } from "react-router-dom";
import MessageToast from "../../components/MessageToast";
import getDate from "../../utils/getDate";
import images from '../../img/images1.png';
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

        let userRegister = {username: inputAll.username, email:inputAll.email, phoneNumber: inputAll.phoneNumber, password: inputAll.password, dateOfJoin: getDate()}; // create user object to send to backend
        
        
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

return (
  <div className="container d-flex justify-content-center align-items-center min-vh-100">
    <div className="row border rounded-5 p-3 bg-white shadow box-area">
      <div className="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box">
        <div className="featured-image mb-3">
          <img src={images} className="img-fluid" style={{ width: '250px' }} alt="Featured" />
        </div>
        <p className="text-white fs-2" style={{ fontFamily: 'Courier New', fontWeight: 600 }}>Be Verified</p>
        <small className="text-white text-wrap text-center" style={{ width: '17rem', fontFamily: 'Courier New' }}>
          Join experienced Designers on this platform.
        </small>
      </div>
      <div className="col-md-6 right-box">
        <div className="row align-items-center">
          <div className="header-text mb-4">
            <h2>Hello, New User</h2>
            <p>We are excited to welcome you!</p>
          </div>
          <form>
            <div className="form-group">
              <label>Username</label>
              <input className="form-control form-control-lg bg-light fs-6" placeholder="Username" type="text" name="username" value={inputAll.username} onChange={handleChange} />
            </div>
            <br />
            <div className="form-group">
              <label>Email</label>
              <input className="form-control form-control-lg bg-light fs-6" placeholder="Email" type="text" name="email" value={inputAll.email} onChange={handleChange} />
            </div>
            <br />
            <div className="form-group">
              <label>Phone Number</label>
              <input className="form-control form-control-lg bg-light fs-6" placeholder="Phone Number" type="text" name="phoneNumber" value={inputAll.phoneNumber} onChange={handleChange} />
            </div>
            <br />
            <div className="form-group">
              <label>Password</label>
              <input className="form-control form-control-lg bg-light fs-6" placeholder="Password" type="text" name="password" value={inputAll.password} onChange={handleChange} />
            </div>
            <br />
            <div className="form-group">
              <label>Choose your role:</label><br />
              <input className="form-check-input" type="radio" name="role" value="user" onChange={handleOption} checked={roleType === "user"} />
              <label style={{ marginLeft: ".2rem" }}>Normal User</label>
              <input className="form-check-input" style={{ marginLeft: "1rem" }} type="radio" name="role" value="admin" onChange={handleOption} checked={roleType === "admin"} />
              <label style={{ marginLeft: ".2rem" }}>Admin</label>
            </div>
            <br />
            <div className="form-group">
              <button type="submit" className="btn btn-lg btn-success w-100 fs-6" onClick={saveUser}>Register</button>
              <span style={{ marginLeft: "1rem" }}>Already registered?
                <Link to="/login">
                  <span style={{ marginLeft: "3px" }}>Login</span>
                </Link>
              </span>
            </div>
            <label style={{ color: "red" }}>{labelWarning}</label>
          </form>
        </div>
      </div>
    </div>
    <div style={{ position: 'fixed', left: 0, bottom: 0 }}>
      <MessageToast show={show} message={message} setShow={setShow} />
    </div>
  </div>
)

}