import React from "react";
import { Input, FormGroup, Label } from "reactstrap";

export default function InputField(props) {
  return (
    <FormGroup>
      <Label>{props.label}</Label>
      <Input
        type="text"
        placeholder={props.label}
        name={props.name}
        value={props.value}
        onChange={props.handleChange}
      />
    </FormGroup>
  );
}
