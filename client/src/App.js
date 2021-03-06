import logo from './logo.svg';
import './App.css';
import { useAuth0 } from '@auth0/auth0-react';

import { useState, useEffect} from 'react';
import configData from "./config.json";
import TaskContainer from './TaskContainer';


function App() {

  const {
    isLoading,
    error,
    isAuthenticated,
    user,
    getAccessTokenSilently,
    loginWithRedirect,
    logout,
  } = useAuth0();
  

  const [accessToken, setAccessToken] = useState(null);
  const [fetchedTasks, setFetchedTasks] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false); 

  useEffect(() => {
      const getAccessToken = async () => {
        try {
          const accessToken = await getAccessTokenSilently({
            audience: configData.audience,
            scope: configData.scope,
          });
          setAccessToken(accessToken);
          setIsLoaded(true);
        } catch (e) {
          console.log(e.message);
        }
      };
      getAccessToken();
    }, [getAccessTokenSilently]);

    useEffect(() => {
      if (!isLoaded) {
        return;
      }

      const getTasks =  async (authId) => {
          fetch("http://localhost:8080/auth0/tasks", 
          {
            method: "GET",
            headers: new Headers({
            Authorization: "Bearer " + accessToken,
            "Content-Type": "application/json",
            }),
          })
          .then(function (res) {
            return res.json();
          })
          .then(function (resJson) {
            setFetchedTasks(resJson);
          })
          .catch((e) => console.log(e));
    }
        getTasks();
        postUser();
    }, [isLoaded]);

    const postUser = (payload) => {
        fetch('http://localhost:8080/auth0/users', {
          method: "POST",
          headers: new Headers({
            Authorization: "Bearer " + accessToken,
            "Content-Type": "application/json",
          })
        })
        .then(res => res.json())
        .catch((e) => console.log(e));
    }

    const securedAPITest = () => {
      fetch("http://localhost:8080/auth0/private", {
        method: "GET",
        headers: new Headers({
          Authorization: "Bearer " + accessToken,
          "Content-Type": "application/json",
        }),
      })
        .then(function (res) {
          return res.json();
        })
        .then(function (resJson) {
          console.log(resJson)
        })
        .catch((e) => console.log(e));
    };

    

  if (error) {
    return <div>Oops... {error.message}</div>;
  }
  
  if (isLoading) {
    return <div>Loading...</div>;
  }
  
  if (!isAuthenticated) {
    return loginWithRedirect();
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>Hi {user.name}, You have successfully logged in.</p>

        <button onClick={() => securedAPITest()}>Test Private API</button>

        <button onClick={() => logout({ returnTo: window.location.origin })}>
          Log Out
        </button>

        <TaskContainer />
      </header>
    </div>
  );
}

export default App;
