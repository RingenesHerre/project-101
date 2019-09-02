import React, { Component } from 'react';
import CompaniesApi from "../services/CompaniesApi";
import Button from "reactstrap/es/Button";
import Modal from "reactstrap/es/Modal";
import ModalHeader from "reactstrap/es/ModalHeader";
import ModalBody from "reactstrap/es/ModalBody";
import Form from "reactstrap/es/Form";

class CreateEmployeeModal extends Component {
    constructor(props){
        super(props);
        this.state={
            firstName:'',
            lastName:'',
            dateOfBirth:'',
            company:'',
            modal: false
        };

        this.handleChange = this.handleChange.bind(this);
        this.toggle= this.toggle.bind(this);
       // this.apiCreateEmployee = this.apiCreateEmployee.bind(this);
    }

    handleChange(event) {
        const name = event.target.name;
        const value = event.target.value;
        this.setState({ [name] : value });
    }

    toggle(){
        this.setState( (prevState) => ({
                modal: !prevState.modal
            }));
    }

    async apiReadAllCompanies() {
        const companies = await CompaniesApi.readAllCompanies();
        this.setState({ companies: companies });
    }

    render() {
        Return (
            <>
                <Button color="primary" onClick={this.toggle}><FaPlus /> New company</Button>
                <Modal>
                    <ModalHeader toggle={this.toggle}>Create new company</ModalHeader>
                    <ModalBody>
                        <Form>
                            <FormGroup>
                                <Lable for="firstName">Firstname</Lable>
                                <Input
                                    type="text"
                                    name="firstName"
                                    id="firstName"
                                    placeholder="Valid firstName"
                                    value={this.state.firstName}
                                    onChange={this.handleChange} />
                            </FormGroup>
                            <FormGroup>
                                <Lable for="lastName">Lastname</Lable>
                                <Input
                                    type="text"
                                    name="lastName"
                                    id="lastName"
                                    placeholder="Valid firstName"
                                    value={this.state.firstName}
                                    onChange={this.handleChange} />
                            </FormGroup>

                        </Form>
                    </ModalBody>
                </Modal>
        )
    }

}