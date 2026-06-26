import os
import re

def process_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    # Simple heuristic to remove block comments that contain typical code indicators like 'return', '=', ';', 'var'
    # Actually, we should just remove // commented code (like in UserDao)
    # We will look for lines starting with // followed by code.
    lines = content.split('\n')
    new_lines = []
    
    in_class = False
    
    for i, line in enumerate(lines):
        stripped = line.strip()
        
        # Remove commented out code (heuristic: // followed by keywords or var/Query/List)
        if stripped.startswith('//'):
            # Only remove if it looks like code and not an actual comment
            if any(keyword in stripped for keyword in ['var ', 'List<', 'Query ', 'return ', 'for(', 'for (', 'entityManager.', 'JpaRepository', 'System.out']):
                continue
            
        # Add basic class javadoc if missing and it's a class declaration
        if not in_class and 'class ' in line and not 'class ' in stripped.split('//')[0] and '{' in line:
            # check if previous lines have javadoc
            has_javadoc = False
            for j in range(max(0, i-5), i):
                if '/**' in lines[j] or '@' in lines[j]:
                    has_javadoc = True
                    break
            
            # Since annotations might be before class, check if the javadoc is before the annotations
            if not has_javadoc:
                # Need to find where to insert. For simplicity, just insert right above the class def if it has no annotations
                # Let's skip automatic class javadoc to avoid breaking annotations.
                pass
            in_class = True
            
        new_lines.append(line)
        
    with open(filepath, 'w', encoding='utf-8') as f:
        f.write('\n'.join(new_lines))

def main():
    root_dir = 'ejemplo-04/src/main/java'
    for subdir, dirs, files in os.walk(root_dir):
        for file in files:
            if file.endswith('.java'):
                process_file(os.path.join(subdir, file))

if __name__ == '__main__':
    main()
