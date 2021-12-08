<p align="center">
 <img width="100px" src="https://www.nvisia.com/hubfs/undefined-358500-edited.jpg" align="center" alt="Dev Ops" />
 <h2 align="center">NAGP DevOps Assignment</h2>
 <p>Name: Sanu Ghosh</p>
 <p>Employee Id: 3151843</p>
 <p>Email: sanu.ghosh@nagarro.com</p>
</p>
<p>
<h2>Prerequisites</h2>
<ul>
  <li>Git - (alternative) download the source code as a zip</li>
  <li>Maven - (alternative) use an IDE with maven plugins installed</li>
  <li>Docker</li>
  <li>MySQL server (Only for Integration testing)- (alternative) use docker with the following command to get MySQL running locally <b><i>docker run --detach --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=test" --name=mysql-docker --publish 3307:3306 --network=app-mysql-network --volume mysql-database-volume:/var/lib/mysql mysql
    </i></b></li>
</ul>
</p>
<p>
  <h2>Steps to run the application</h2>
  <ul>
    <li>
      Clone from the repository or download the source code
      <ul>
        <li>
        Cloning - <b><i>git clone https://github.com/Sanu07/nagp-devops-microservices.git</b></i>
        </li>
        <li>
        Downloading - Download it as a zip
        </li>
      </ul>
    </li>
    <li>Build the application - Navigate to the source location where the project has been downloaded or cloned. Then run <b><i>mvn clean package</i></b>
      <img src="https://img.shields.io/badge/build-passing-brightgreen"/>
  </li>
    <li>
      Run Unit tests - <b><i>mvn test -P unit-test</b></i> <img src="https://shields.io/badge/test-passing-brightgreen"/> <img src="https://img.shields.io/badge/total%20Unit%20tests-5-brightgreen"/>
  </li>
  <li>
    Run Integration tests - <b><i>mvn verify -P integration-test</b></i> <img src="https://shields.io/badge/test-passing-brightgreen"/> <img src="https://img.shields.io/badge/total%20Integration%20tests-4-brightgreen"/>
  </li>
  <li>Run the application - <b><i>docker compose up</b></i></li> <img src="https://shields.io/badge/docker%20build-passing-brightgreen"/> <img src="https://shields.io/badge/docker%20build-automated-066da5"/>
  </ul>
</p>                                                  

