# HTML Analyzer

This project is an HTML analyzer designed to extract the text found at the deepest level of nesting within an HTML structure. The solution was developed for a technical assessment that prohibits the use of HTML, XML, or DOM parsing libraries. This repository also comes with a simple test HTML file.

## Features

- Efficient O(n) solution that processes the HTML in a single pass using a stack-based approach.
- Handles various edge cases such as:
  - Malformed HTML
  - Unclosed tags
  - Deeply nested structures
- Outputs the deepest-level text found in the provided HTML content.

## Requirements

- **Java 17** or higher
- **Internet connection** (for fetching HTML from URLs)

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/LuizBronzeadoNeto/HtmlAnalyzer.git
   ```
2. Compile the Java code:
   ```sh
   javac HtmlAnalyzer.java
   ```

## Usage

To run the HTML analyzer, use the following command:

```sh
java HtmlAnalyzer <URL>
```

### Example

```sh
java HtmlAnalyzer https://example.com/sample.html
```

**Output:**

```
Deepest text content found here.
```

### Error Handling

- If the URL is invalid or unreachable, the program will print:

```
URL connection error
```

- If the HTML is malformed (e.g., tags aren't properly closed), the program will print:

```
malformed HTML
```

## Testing

To test locally with sample HTML files:

1. Run a local server (e.g., using Python):
   ```sh
   python -m http.server 8000
   ```
2. Access the file via:
   ```sh
   java HtmlAnalyzer http://localhost:8000/sample.html
   ```

## Known Limitations

- The code assumes the HTML content will always have each tag or text on separate lines.
- It does not support self-closing tags like `<img>` or `<meta>`.

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

For inquiries or issues, please contact: [**luizbronzeadoneto@gmail.com**](mailto\:luizbronzeadoneto@gmail.com)
