
import '@polymer/polymer/lib/elements/custom-style.js';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `<dom-module theme-for="vaadin-grid" id="my-grid-styles">
        <template>
            <style>
                @media only screen and (max-width: 768px) {
                    /* For mobile phones: */
                    [part*="cell"].gender-column {
                        display: none;
                    }
                
                    [part*="header-cell"].gender-column {
                        display: none;
                    }
                }
            
                [part*="cell header-cell"] .gender-column {
                    color: red;
                }
            </style>
        </template>
    </dom-module>
        
    <custom-style>
        <style>
            
        </style>
    </custom-style>`;

document.head.appendChild($_documentContainer.content);

