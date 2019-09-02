import React, {Component} from 'react';
import CardBody from "reactstrap/es/CardBody";
import {FaSyncAlt} from "react-icons";
import Button from "reactstrap/es/Button";
import Card from "reactstrap/es/Card";
import CardTitle from "reactstrap/es/CardTitle";
import CardText from "reactstrap/es/CardText";


class EmployeeListPage extends Component {
    constructor(props) {
        super(props);

    }

    render() {
        return (
            <div>
                Dette er en test
            </div>
        )
    }



    componentDidMount() {
        this.apiReadAllEmployees();
    }

    async apiReadAllEmployees() {
        const employees = await EmployeesApi.ReadAllEmployees();
        this.setState({employees: employees});
    }

    render(){
        const employees = this.state.employees;

        // let employeeRows = [];
        // employees.map((employee) => {
        //     return employeeRows.push(
        //         <tr key={employee.id}>
        //             <th scope="row">{employee.id}</th>
        //             <td>{employee.firstName}</td>
        //             <td>{employee.lastName}</td>
        //             <td>{employee.dateOfBirth}</td>
        //             <td>{employee.companyId}</td>
        //     )
        // })

        const employeesTable = (
            <Table dark striped>
                <thead>
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">firstName</th>
                        <th scope="col">lastName</th>
                        <th scope="col">dateOfBirth</th>
                        <th scope="col">companyId</th>
                        <th scope="col">actions</th>
                    </tr>
                </thead>
                <tbody>
                    //employeeRows
                </tbody>
            </Table>
        );

        const emptyTable = (
            <p>No employees yet, use button above to add one!</p>
        );

        return (
            <Card color = "white" className="shadow p-3 mb-5 rounded">
                <CardBody>
                    <CardTitle tag="h3"><FaBuilding /> List of employees</CardTitle>
                    <div className="card-action">
                        <Button color="secondary" onClick={this.apiReadAllEmployees}><FaSyncAlt /></Button> {' '}
                        <CreateEmployeeModal onCreated={this.apiReadAllEmployees} />
                    </div>
                    <CardText tag="div">
                        {employees.length > 0 ? employeesTable : emptyTable}
                    </CardText>
                </CardBody>
            </Card>
        );
    }
}

export default EmployeeListPage;