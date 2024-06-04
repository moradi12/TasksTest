// export class Task {
//     id: number;
//     taskName: string;
//     addTask: string;
//     deleteTask: string;
//     updateTask: string;
//     isComplete: boolean;
//     dateOfExecution: Date;
//     personId: number;
  
//     constructor(
//       id: number,
//       taskName: string,
//       addTask: string,
//       deleteTask: string,
//       updateTask: string,
//       isComplete: boolean,
//       dateOfExecution: Date,
//       personId: number
//     ) {
//       this.id = id;
//       this.taskName = taskName;
//       this.addTask = addTask;
//       this.deleteTask = deleteTask;
//       this.updateTask = updateTask;
//       this.isComplete = isComplete;
//       this.dateOfExecution = dateOfExecution;
//       this.personId = personId;
//     }
//   }
 import IPerson from './IPerson';
 
  export default interface ITask {
    description: string;
    id: number;
    taskName: string;
    complete: boolean;
    dateOfExecution: Date;
    person: IPerson;
  }  