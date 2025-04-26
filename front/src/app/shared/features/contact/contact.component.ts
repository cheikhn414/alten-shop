import {Component, signal} from '@angular/core';
import {Button} from "primeng/button";
import {DropdownModule} from "primeng/dropdown";
import {FormsModule, NgForm} from "@angular/forms";
import {InputNumberModule} from "primeng/inputnumber";
import {InputTextModule} from "primeng/inputtext";
import {InputTextareaModule} from "primeng/inputtextarea";
import {CardModule} from "primeng/card";
import {MessageModule} from "primeng/message";

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [
    Button,
    DropdownModule,
    FormsModule,
    InputNumberModule,
    InputTextModule,
    InputTextareaModule,
    CardModule,
    MessageModule
  ],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent {
  email: string = '';
  message: string = '';

  notification: any = {
    visible: signal(false),
    message: '',
    severity: 'info',
  }

  showNotification(message: string, severity: string) {
    this.notification.visible.set(true);
    this.notification.message = message;
    this.notification.severity = severity;

    setTimeout(() => {
      this.notification.visible.set(false);
    }, 5500);
  }

  onSend(form: NgForm) {
    console.log(form.value);
    if (form.valid && (form.value.email && form.value.message)) {
      this.showNotification('Demande de contact envoyée avec succès', 'success');
      form.resetForm();
    } else {
      this.showNotification("Une erreur est survenue lors de l'envoie de votre message. Veuillez réessayer plutard." , 'error');
    }
  }
}
