import React, {Component} from 'react';


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

        )

    }
}

export default EmployeeListPage;