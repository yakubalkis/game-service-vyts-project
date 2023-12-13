import { useNavigate } from 'react-router';


export default function Logout(){

    const navigate = useNavigate();
    const username = localStorage.getItem("usernameOfCurrentUser"); // get username no from local storage

    function handleLogout(){
        localStorage.removeItem("usernameOfCurrentUser"); // remove items from local storage
        localStorage.removeItem("tokenKey");

        navigate("/login"); // redirect to login page
        window.location.reload();
    }

    return(
        <div style={{position:"absolute", right: "2rem"}}>
            <label style={{color:"white", marginRight:"1rem"}}>{`Username: `+ username}</label>
            <button className='btn btn-warning' onClick={() => handleLogout()}>Logout <>&#9756;</></button>
        </div>
    )
}