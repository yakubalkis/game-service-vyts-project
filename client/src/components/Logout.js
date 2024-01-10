import { useNavigate } from 'react-router';



export default function Logout(){
    const navigate = useNavigate();
    const username = JSON.parse(localStorage.getItem("user")); // get username no from local storage
    

    function handleLogout(){
        localStorage.removeItem("user"); // remove items from local storage
        localStorage.removeItem("isReloaded");
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